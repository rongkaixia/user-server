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
// import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Updates._
import org.bson.types.ObjectId

import com.echo.protocol.captain._
import com.echo.protocol.common._
import com.echo.captain.utils.Jwt

trait UpdateUserCartImpl extends AbstractCaptainService with LazyLogging{

  private def saveToMongo(userId: String, item: CartItem): Future[Unit] = {
    async{
      val dbName = cfg.getString("echo.captain.mongo.user.db")
      val collectionName = cfg.getString("echo.captain.mongo.user.collection")
      val userIdColumn = cfg.getString("echo.captain.mongo.user.columns.user_id")
      val cartColumn = cfg.getString("echo.captain.mongo.user.columns.cart")
      val skuIdColumn = cfg.getString("echo.captain.mongo.user.columns.sku_id")
      val numColumn = cfg.getString("echo.captain.mongo.user.columns.cart_item_num")
      val createAtColumn = cfg.getString("echo.captain.mongo.user.columns.cart_item_create_at")
      val updateAtColumn = cfg.getString("echo.captain.mongo.user.columns.cart_item_update_at")
      logger.debug(s"mongo database = ${dbName}, collection = ${collectionName}")
      val database: MongoDatabase = mongo.getDatabase(dbName)
      val collection = database.getCollection(collectionName)

      val currentTime = java.time.Instant.now.toEpochMilli

      // update mongo
      var filterOp = and(equal(userIdColumn, userId), 
                         elemMatch(cartColumn, equal(skuIdColumn, item.skuId)))
      var updateOp = and(set(s"${cartColumn}.$$.${numColumn}", item.num),
                         set(s"${cartColumn}.$$.${updateAtColumn}", new bson.BsonDateTime(currentTime)))
      val result = await(collection.updateOne(filterOp, updateOp).toFuture)
      if (result.head.getMatchedCount == 0) {
        // append a new cart item
        logger.debug(s"${item} not exist, now appending a new cart item into user cart")
        var doc = Document(JsonFormat.toJsonString(item)) - createAtColumn - updateAtColumn
        doc += (updateAtColumn -> new bson.BsonDateTime(currentTime))
        doc += (createAtColumn -> new bson.BsonDateTime(currentTime))
        logger.debug(s"Document=${doc}")
        filterOp = equal(userIdColumn, userId)
        updateOp = addToSet(cartColumn, doc)
        await(collection.updateOne(filterOp, updateOp).toFuture)
      } else {
        logger.debug("update user cart item success")
      }
    }
  }
  
  /**
   * logout interface
   *
   * @type  req UpdateUserCartRequest
   * @return UpdateUserCartResponse
   */
  override def updateUserCart(req: UpdateUserCartRequest): Future[UpdateUserCartResponse] = {
    val replyPromise = Promise[UpdateUserCartResponse]()
    logger.debug(s"recieve UpdateUserCart request: ${req}")

    val fut = async{
      val userIdColumn = cfg.getString("echo.captain.mongo.user.columns.user_id")
      var res = UpdateUserCartResponse()

      // check request
      val token = req.token
      val skuId = req.skuId
      val num = req.num
      if (token.isEmpty){
        val header = ResponseHeader(ResultCode.INVALID_SESSION_TOKEN, "INVALID_SESSION_TOKEN")
        res = res.withHeader(header)
      }else if (skuId.isEmpty) {
        val header = ResponseHeader(ResultCode.INVALID_REQUEST_ARGUMENT, "skuId MUST NOT be empty")
        res = res.withHeader(header)
      }else if (num <= 0) {
        val header = ResponseHeader(ResultCode.INVALID_REQUEST_ARGUMENT, "num MUST NOT be greated than 0")
        res = res.withHeader(header)
      }else{
        // check auth
        logger.debug(s"checkAuth for token ${token}")
        val resultMap = await(queryAuth(token))
        if (resultMap.isEmpty) {
          val header = ResponseHeader(ResultCode.SESSION_TOKEN_EXPIRED, "session token has expired")
          res = res.withHeader(header)
        } else {
          // update cart
          val userId = resultMap(userIdColumn).asString.getValue
          val cartItem = CartItem(skuId = skuId, num = num)
          await(saveToMongo(userId, cartItem))
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
        logger.error(s"UpdateUserCart error: ${error}")
        val header = ResponseHeader(ResultCode.INTERNAL_SERVER_ERROR, error.toString)
        replyPromise success UpdateUserCartResponse().withHeader(header)
    }

    // send response
    replyPromise.future
  }
}