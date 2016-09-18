package com.echo.captain

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.async.Async.{async, await}

import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.{Logger, LazyLogging}
import org.slf4j.LoggerFactory
import com.trueaccord.scalapb.json.JsonFormat

import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Updates._
import org.bson.types.ObjectId

import com.echo.protocol.captain._
import com.echo.protocol.common._
import com.echo.captain.utils.Jwt

trait SignupImpl extends AbstractCaptainService with LazyLogging{

  private def saveToMongo(userInfo: UserInfo): Future[Unit] = {
    async{
      val dbName = cfg.getString("echo.captain.mongo.user.db")
      val collectionName = cfg.getString("echo.captain.mongo.user.collection")
      logger.debug(s"mongo database = ${dbName}, collection = ${collectionName}")
      val database: MongoDatabase = mongo.getDatabase(dbName)
      val collection = database.getCollection(collectionName)
      val doc = Document(JsonFormat.toJsonString(userInfo))
      logger.debug(s"Document=${doc}")
      await(collection.insertOne(doc).toFuture)
    }
  }

  /**
   * signup interface
   *
   * @type  req SignupRequest
   * @return SignupResponse
   */
  override def signup(req: SignupRequest): Future[SignupResponse] = {
    val replyPromise = Promise[SignupResponse]()
    logger.debug(s"recieve signup request: ${req}")
    val fut = async{
      val tokenExpiresIn = cfg.getInt("echo.captain.token_expires_in")
      val jwtSecretKey = cfg.getString("echo.captain.jwt_secret_key")
      var res = SignupResponse()

      // check request
      val phonenum = req.phonenum
      val password = req.password
      if (phonenum.isEmpty){
        val header = ResponseHeader(ResultCode.INVALID_PHONENUM, "phonenum cannot be empty.")
        res = res.withHeader(header)
      }else if(password.isEmpty){
        val header = ResponseHeader(ResultCode.INVALID_PASSWORD, "password cannot be empty.")
        res = res.withHeader(header)
      }else{
        // checking whether the phonenum is existed
        val isExisted = await(isPhonenumExisted(phonenum))
        logger.debug("isPhonenumExisted: " + isExisted)
        if (isExisted){
          val header = ResponseHeader(ResultCode.PHONENUM_ALREADY_EXISTED, "phonenum[" + phonenum + "] already existed.")
          res = res.withHeader(header)
        }else{
          // generate user id
          val userId = new ObjectId
          val username = phonenum
          val userInfo = UserInfo(userId = userId.toString, username = username, phonenum = phonenum)

          // write to db
          logger.info("save user info to db...")
          await(saveToMongo(userInfo))

          logger.info(s"generate Json Web Token with ${userId}...")
          val token = Jwt.generateToken(userId.toString, jwtSecretKey)

          logger.info("token: " + token)
          await(saveAuthToken(authToken = token, 
                             authName = AuthType.LOCAL.toString, 
                             authID = AuthType.LOCAL.toString, 
                             userID = userId.toString, 
                             username = username, 
                             expiresIn = tokenExpiresIn))

          // success response
          val header = ResponseHeader(ResultCode.SUCCESS, "ok")
          res = res.withHeader(header)
                   .withUserId(userId.toString)
                   .withUsername(username)
                   .withToken(token)
                   .withExpiresIn(tokenExpiresIn)
        }
      }
      // response
      replyPromise success res
    }

    // exception, because await must not be used under a try/catch.
    fut.onFailure {
      case error: Throwable => 
        logger.error(s"signup error: ${error}")
        val header = ResponseHeader(ResultCode.INTERNAL_SERVER_ERROR, error.toString)
        replyPromise success SignupResponse().withHeader(header)
    }

    // send response
    replyPromise.future
  }
}