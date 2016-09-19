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

trait LoginImpl extends AbstractCaptainService with LazyLogging{

  /**
   * @return  (isCorrected: Boolean, userID: String, username: String)
   */
  private def isPasswordCorrected(phonenum: String, 
                                  password: String): Future[(Boolean, String, String)] = {
    async{
      val dbName = cfg.getString("echo.captain.mongo.user.db")
      val collectionName = cfg.getString("echo.captain.mongo.user.collection")
      val phonenumColumn = cfg.getString("echo.captain.mongo.user.columns.phonenum")
      val userIdColumn = cfg.getString("echo.captain.mongo.user.columns.user_id")
      val usernameColumn = cfg.getString("echo.captain.mongo.user.columns.username")
      val database: MongoDatabase = mongo.getDatabase(dbName)
      val collection = database.getCollection(collectionName)

      val filterOp = equal(phonenumColumn, phonenum)
      val projectionOp = exclude("_id")
      val result = await(collection.find(filterOp).projection(projectionOp).first().toFuture)
      if (result.size != 1) {
        (false, null, null)
      }else {
        val resultMap = result.head.toMap
        if (resultMap(phonenumColumn).asString.getValue != password) {
          (false, null, null)
        }else {
          (true, resultMap(userIdColumn).asString.getValue, resultMap(usernameColumn).asString.getValue)
        }
      }
    }
  }

  /**
   * login interface
   *
   * @type  req LoginRequest
   * @return LoginResponse
   */
  override def login(req: LoginRequest): Future[LoginResponse] = {
    val replyPromise = Promise[LoginResponse]()
    logger.debug(s"recieve login request: ${req}")

    val fut = async{
      val tokenExpiresIn = cfg.getInt("echo.captain.token_expires_in")
      val jwtSecretKey = cfg.getString("echo.captain.jwt_secret_key")
      var res = LoginResponse()

      // check request
      val phonenum = req.getPhonenum
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
        if (!isExisted){
          val header = ResponseHeader(ResultCode.INVALID_PHONENUM, "phonenum[" + phonenum + "] not existed.")
          res = res.withHeader(header)
        }else{
          val (isCorrect, userId, username) = await(isPasswordCorrected(phonenum, password))
          logger.debug("isPasswordCorrected: " + isCorrect)
          if (!isCorrect){
            val header = ResponseHeader(ResultCode.INVALID_PASSWORD, "password incorrected.")
            res = res.withHeader(header)
          }else{
            logger.info(s"generate Json Web Token with ${userId}...")
            val token = Jwt.generateToken(userId, jwtSecretKey)

            logger.info("token: " + token)
            await(saveAuthToken(authToken = token, 
                               authName = AuthType.LOCAL.toString, 
                               authID = AuthType.LOCAL.toString, 
                               userID = userId, 
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
      }
      // response
      replyPromise success res
    }

    // exception, because await must not be used under a try/catch.
    fut.onFailure {
      case error: Throwable => 
        logger.error(s"login error: ${error}")
        val header = ResponseHeader(ResultCode.INTERNAL_SERVER_ERROR, error.toString)
        replyPromise success LoginResponse().withHeader(header)
    }

    // send response
    replyPromise.future
  }
}