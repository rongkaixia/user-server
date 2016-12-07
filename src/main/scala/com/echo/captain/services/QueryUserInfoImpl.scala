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

trait QueryUserInfoImpl extends AbstractCaptainService with LazyLogging{

  private def queryMongo(userId: String, fields: String*): Future[UserInfo] = {
    async{
      val dbName = cfg.getString("echo.captain.mongo.user.db")
      val collectionName = cfg.getString("echo.captain.mongo.user.collection")
      val userIdColumn = cfg.getString("echo.captain.mongo.user.columns.user_id")
      val passwordColumn = cfg.getString("echo.captain.mongo.user.columns.password")
      logger.debug(s"mongo database = ${dbName}, collection = ${collectionName}")
      val database: MongoDatabase = mongo.getDatabase(dbName)
      val collection = database.getCollection(collectionName)

      val filterOp = equal(userIdColumn, userId)
      val projectionOp = exclude("_id", passwordColumn)
      val result = await(collection.find(filterOp).projection(projectionOp).first().toFuture)
      if (result.size != 1) {
        logger.debug(s"queryMongo error: user not exist for userId[${userId}]")
        throw new CaptainServiceException.UserNotExist(userId)
      }
      logger.debug(s"userInfo: ${result.head}")
      JsonFormat.fromJsonString[UserInfo](result.head.toJson)
    }
  }

  /**
   * logout interface
   *
   * @type  req QueryUserInfoRequest
   * @return QueryUserInfoResponse
   */
  override def queryUserInfo(req: QueryUserInfoRequest): Future[QueryUserInfoResponse] = {
    val replyPromise = Promise[QueryUserInfoResponse]()
    logger.debug(s"recieve queryUserInfo request: ${req}")

    val fut = async{
      val userIdColumn = cfg.getString("echo.captain.mongo.user.columns.user_id")
      var res = QueryUserInfoResponse()

      // check request
      val token = req.token
      val fields = req.fields
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
          val userInfo = await(queryMongo(userId, fields: _*))
          val header = ResponseHeader(ResultCode.SUCCESS, "ok")
          res = res.withHeader(header).withUserInfo(userInfo)
        }
      }

      // response
      replyPromise success res
    }

    // exception, because await must not be used under a try/catch.
    fut.onFailure {
      case error: Throwable => 
        logger.error(s"queryUserInfo error: ${error}")
        val header = ResponseHeader(ResultCode.INTERNAL_SERVER_ERROR, error.toString)
        replyPromise success QueryUserInfoResponse().withHeader(header)
    }

    // send response
    replyPromise.future
  }
}