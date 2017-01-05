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

trait DeleteUserCartImpl extends AbstractCaptainService with LazyLogging{
  /**
   * logout interface
   *
   * @type  req DeleteUserCartRequest
   * @return DeleteUserCartResponse
   */
  override def deleteUserCart(req: DeleteUserCartRequest): Future[DeleteUserCartResponse] = {
    val replyPromise = Promise[DeleteUserCartResponse]()
    logger.debug(s"recieve DeleteUserCart request: ${req}")

    val fut = async{
      var res = DeleteUserCartResponse()
      val dbName = cfg.getString("echo.captain.mongo.user.db")
      val collectionName = cfg.getString("echo.captain.mongo.user.collection")
      val userIdColumn = cfg.getString("echo.captain.mongo.user.columns.user_id")
      val cartColumn = cfg.getString("echo.captain.mongo.user.columns.cart")
      val skuIdColumn = cfg.getString("echo.captain.mongo.user.columns.sku_id")

      // check request
      val token = req.token
      val skuId = req.skuId
      if (token.isEmpty){
        val header = ResponseHeader(ResultCode.INVALID_SESSION_TOKEN, "INVALID_SESSION_TOKEN")
        res = res.withHeader(header)
      }else if(skuId.isEmpty){
        val header = ResponseHeader(ResultCode.INVALID_REQUEST_ARGUMENT, "skuId MUST NOT be empty")
        res = res.withHeader(header)
      }else{
        // check auth
        logger.debug(s"checkAuth for token ${token}")
        val resultMap = await(queryAuth(token))
        if (resultMap.isEmpty) {
          val header = ResponseHeader(ResultCode.SESSION_TOKEN_EXPIRED, "session token has expired")
          res = res.withHeader(header)
        } else {
          // delete cart item
          val userId = resultMap(userIdColumn).asString.getValue

          val database: MongoDatabase = mongo.getDatabase(dbName)
          val collection = database.getCollection(collectionName)
          val filterOp = equal(userIdColumn, userId)
          val updateOp = pull(cartColumn, equal(skuIdColumn, skuId))
          await(collection.updateOne(filterOp, updateOp).toFuture)

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
        logger.error(s"DeleteUserCart error: ${error}")
        val header = ResponseHeader(ResultCode.INTERNAL_SERVER_ERROR, error.toString)
        replyPromise success DeleteUserCartResponse().withHeader(header)
    }

    // send response
    replyPromise.future
  }
}