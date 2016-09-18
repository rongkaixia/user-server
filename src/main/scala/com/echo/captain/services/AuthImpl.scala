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

trait AuthImpl extends AbstractCaptainService with LazyLogging{
  /**
   * logout interface
   *
   * @type  req AuthRequest
   * @return AuthResponse
   */
  override def auth(req: AuthRequest): Future[AuthResponse] = {
    val replyPromise = Promise[AuthResponse]()
    logger.debug(s"recieve logout request: ${req}")

    val fut = async{
      val userIdColumn = cfg.getString("echo.captain.mongo.auth.columns.user_id")
      val usernameColumn = cfg.getString("echo.captain.mongo.auth.columns.username")
      val expireAtColumn = cfg.getString("echo.captain.mongo.auth.columns.expire_at")
      var res = AuthResponse()

      // check request
      val token = req.token
      if (token.isEmpty){
        val header = ResponseHeader(ResultCode.SUCCESS, "ok")
        res = res.withHeader(header)
      }else{
        logger.debug(s"checkAuth for token ${token}")
        val resultMap = await(queryAuth(token))
        if (resultMap.isEmpty) {
          val header = ResponseHeader(ResultCode.SESSION_TOKEN_EXPIRED, "session token has expired")
          res = res.withHeader(header)
        } else {
          val userId = resultMap(userIdColumn).asString.getValue
          val username = resultMap(usernameColumn).asString.getValue
          val expireAt = resultMap(expireAtColumn).asDateTime.getValue
          val expireIn = (expireAt - java.time.Instant.now.toEpochMilli) / 1000
          val header = ResponseHeader(ResultCode.SUCCESS, "ok")
          res = res.withHeader(header)
                   .withUserId(userId)
                   .withUsername(username)
                   .withExpiresIn(expireIn.toInt)
        }
      }

      // response
      replyPromise success res
    }

    // exception, because await must not be used under a try/catch.
    fut.onFailure {
      case error: Throwable => 
        logger.error(s"logout error: ${error}")
        val header = ResponseHeader(ResultCode.INTERNAL_SERVER_ERROR, error.toString)
        replyPromise success AuthResponse().withHeader(header)
    }

    // send response
    replyPromise.future
  }
}