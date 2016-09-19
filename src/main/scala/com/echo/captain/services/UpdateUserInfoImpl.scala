package com.echo.captain

import scala.collection.mutable
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
// import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Updates._
import org.bson.types.ObjectId

import com.echo.protocol.captain._
import com.echo.protocol.common._
import com.echo.captain.utils.Jwt

trait UpdateUserInfoImpl extends AbstractCaptainService with LazyLogging{
  private def saveToMongo(userId: String, 
                          username: Option[String],
                          email: Option[String],
                          phonenum: Option[String],
                          securityQuestion1: Option[SecurityQuestionPair],
                          securityQuestion2: Option[SecurityQuestionPair],
                          securityQuestion3: Option[SecurityQuestionPair]): Future[Unit] = {
    async{
      val dbName = cfg.getString("echo.captain.mongo.user.db")
      val collectionName = cfg.getString("echo.captain.mongo.user.collection")
      val userIdColumn = cfg.getString("echo.captain.mongo.user.columns.user_id")
      val usernameColumn = cfg.getString("echo.captain.mongo.user.columns.username")
      val emailColumn = cfg.getString("echo.captain.mongo.user.columns.email")
      val phonenumColumn = cfg.getString("echo.captain.mongo.user.columns.phonenum")
      val securityQuestion1Column = cfg.getString("echo.captain.mongo.user.columns.security_question1")
      val securityQuestion2Column = cfg.getString("echo.captain.mongo.user.columns.security_question2")
      val securityQuestion3Column = cfg.getString("echo.captain.mongo.user.columns.security_question3")
      logger.debug(s"mongo database = ${dbName}, collection = ${collectionName}")
      val database: MongoDatabase = mongo.getDatabase(dbName)
      val collection = database.getCollection(collectionName)

      // updateOps
      val updateOps = mutable.ArrayBuffer[bson.conversions.Bson]()
      if (username.isDefined) updateOps.append(set(usernameColumn,username.get))
      if (email.isDefined) updateOps.append(set(emailColumn,email.get))
      if (phonenum.isDefined) updateOps.append(set(phonenumColumn,phonenum.get))
      if (securityQuestion1.isDefined) 
        updateOps.append(set(securityQuestion1Column,Document(JsonFormat.toJsonString(securityQuestion1.get))))
      if (securityQuestion2.isDefined)
        updateOps.append(set(securityQuestion2Column,Document(JsonFormat.toJsonString(securityQuestion2.get))))
      if (securityQuestion3.isDefined)
        updateOps.append(set(securityQuestion3Column,Document(JsonFormat.toJsonString(securityQuestion3.get))))

      val filterOp = equal(userIdColumn, userId)
      val updateOp = combine(updateOps: _*)
      await(collection.updateOne(filterOp, updateOp).toFuture)
    }
  }
  
  /**
   * logout interface
   *
   * @type  req UpdateUserInfoRequest
   * @return UpdateUserInfoResponse
   */
  override def updateUserInfo(req: UpdateUserInfoRequest): Future[UpdateUserInfoResponse] = {
    val replyPromise = Promise[UpdateUserInfoResponse]()
    logger.debug(s"recieve updateUserInfo request: ${req}")

    val fut = async{
      var res = UpdateUserInfoResponse()
      val userIdColumn = cfg.getString("echo.captain.mongo.user.columns.user_id")
      val usernameColumn = cfg.getString("echo.captain.mongo.user.columns.username")
      val emailColumn = cfg.getString("echo.captain.mongo.user.columns.email")
      val phonenumColumn = cfg.getString("echo.captain.mongo.user.columns.phonenum")
      val securityQuestion1Column = cfg.getString("echo.captain.mongo.user.columns.security_question1")
      val securityQuestion2Column = cfg.getString("echo.captain.mongo.user.columns.security_question2")
      val securityQuestion3Column = cfg.getString("echo.captain.mongo.user.columns.security_question3")
      val allowUpdateColumns = Seq(usernameColumn, 
                                   emailColumn, 
                                   phonenumColumn,
                                   securityQuestion1Column,
                                   securityQuestion2Column,
                                   securityQuestion3Column)

      // check request
      val token = req.token
      val username: Option[String] = if(req.username.isEmpty) None else Some(req.username)
      val email: Option[String] = if(req.email.isEmpty) None else Some(req.email)
      val phonenum: Option[String] = if(req.phonenum.isEmpty) None else Some(req.phonenum)
      val securityQuestion1: Option[SecurityQuestionPair] = req.securityQuestion1
      val securityQuestion2: Option[SecurityQuestionPair] = req.securityQuestion2
      val securityQuestion3: Option[SecurityQuestionPair] = req.securityQuestion3
      if (token.isEmpty){
        val header = ResponseHeader(ResultCode.INVALID_SESSION_TOKEN, "INVALID_SESSION_TOKEN")
        res = res.withHeader(header)
      }else{
        logger.debug(s"checkAuth for token ${token}")
        val resultMap = await(queryAuth(token))
        if (resultMap.isEmpty) {
          val header = ResponseHeader(ResultCode.SESSION_TOKEN_EXPIRED, "session token has expired")
          res = res.withHeader(header)
        } else {
          val userId = resultMap(userIdColumn).asString.getValue

          await(saveToMongo(userId, 
                            username, 
                            email, 
                            phonenum, 
                            securityQuestion1, 
                            securityQuestion2,
                            securityQuestion3))
          val header = ResponseHeader(ResultCode.SUCCESS, "ok")
          res = res.withHeader(header)
        }
      }

      // response
      replyPromise success res
    }

    // exception, because await must not be used under a try/catch.
    fut.onFailure {
      case error: Throwable => 
        logger.error(s"updateUserInfo error: ${error}")
        val header = ResponseHeader(ResultCode.INTERNAL_SERVER_ERROR, error.toString)
        replyPromise success UpdateUserInfoResponse().withHeader(header)
    }

    // send response
    replyPromise.future
  }
}