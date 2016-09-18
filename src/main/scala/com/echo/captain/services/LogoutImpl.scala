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

trait LogoutImpl extends AbstractCaptainService with LazyLogging{

  private def clearAuthToken(authToken: String): Future[Unit] = {
    async{
      val dbName = cfg.getString("echo.captain.mongo.auth.db")
      val collectionName = cfg.getString("echo.captain.mongo.auth.collection")
      val authTokenColumn = cfg.getString("echo.captain.mongo.auth.columns.auth_access_token")

      val database: MongoDatabase = mongo.getDatabase(dbName)
      val collection = database.getCollection(collectionName)

      val filterOp = equal(authTokenColumn, authToken)
      await(collection.deleteMany(filterOp).toFuture)
    }
  }

  /**
   * logout interface
   *
   * @type  req LogoutRequest
   * @return LogoutResponse
   */
  override def logout(req: LogoutRequest): Future[LogoutResponse] = {
    val replyPromise = Promise[LogoutResponse]()
    logger.debug(s"recieve logout request: ${req}")

    val fut = async{
      var res = LogoutResponse()

      // check request
      val token = req.token
      if (token.isEmpty){
        val header = ResponseHeader(ResultCode.SUCCESS, "ok")
        res = res.withHeader(header)
      }else{
        logger.debug("clearing token")
        await(clearAuthToken(token))
        logger.debug("clear token success")
        val header = ResponseHeader(ResultCode.SUCCESS, "ok")
        res = res.withHeader(header)
      }

      // response
      replyPromise success res
    }

    // exception, because await must not be used under a try/catch.
    fut.onFailure {
      case error: Throwable => 
        logger.error(s"signup error: ${error}")
        val header = ResponseHeader(ResultCode.INTERNAL_SERVER_ERROR, error.toString)
        replyPromise success LogoutResponse().withHeader(header)
    }

    // send response
    replyPromise.future
  }
}