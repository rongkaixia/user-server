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

trait UpdateUserAddressImpl extends AbstractCaptainService with LazyLogging{

  private def saveToMongo(userId: String, userAddress: UserAddress): Future[Unit] = {
    async{
      val dbName = cfg.getString("echo.captain.mongo.user.db")
      val collectionName = cfg.getString("echo.captain.mongo.user.collection")
      val userIdColumn = cfg.getString("echo.captain.mongo.user.columns.user_id")
      val addressesColumn = cfg.getString("echo.captain.mongo.user.columns.addresses")
      val addressIdColumn = cfg.getString("echo.captain.mongo.user.columns.address_id")
      logger.debug(s"mongo database = ${dbName}, collection = ${collectionName}")
      val database: MongoDatabase = mongo.getDatabase(dbName)
      val collection = database.getCollection(collectionName)

      val doc = Document(JsonFormat.toJsonString(userAddress))
      logger.debug(s"Document=${doc}")
      val filterOp = and(equal(userIdColumn, userId), 
                         elemMatch(addressesColumn, equal(addressIdColumn, userAddress.addressId)))
      val updateOp = set(s"${addressesColumn}.$$", doc)
      await(collection.updateOne(filterOp, updateOp).toFuture)
    }
  }
  
  /**
   * logout interface
   *
   * @type  req UpdateUserAddressRequest
   * @return UpdateUserAddressResponse
   */
  override def updateUserAddress(req: UpdateUserAddressRequest): Future[UpdateUserAddressResponse] = {
    val replyPromise = Promise[UpdateUserAddressResponse]()
    logger.debug(s"recieve UpdateUserAddress request: ${req}")

    val fut = async{
      val userIdColumn = cfg.getString("echo.captain.mongo.user.columns.user_id")
      var res = UpdateUserAddressResponse()

      // check request
      val token = req.token
      val addressId = req.addressId
      val recipientsName = req.recipientsName
      val recipientsPhone = req.recipientsPhone
      val recipientsAddress = req.recipientsAddress
      val recipientsPostcode = req.recipientsPostcode
      if (token.isEmpty){
        val header = ResponseHeader(ResultCode.INVALID_SESSION_TOKEN, "INVALID_SESSION_TOKEN")
        res = res.withHeader(header)
      }else if (addressId.isEmpty) {
        val header = ResponseHeader(ResultCode.INVALID_REQUEST_ARGUMENT, "addressId MUST NOT be empty")
        res = res.withHeader(header)
      }else{
        logger.debug(s"checkAuth for token ${token}")
        val resultMap = await(queryAuth(token))
        if (resultMap.isEmpty) {
          val header = ResponseHeader(ResultCode.SESSION_TOKEN_EXPIRED, "session token has expired")
          res = res.withHeader(header)
        } else {
          val userId = resultMap(userIdColumn).asString.getValue
          val address = new UserAddress(addressId = addressId,
                                        recipientsName = recipientsName,
                                        recipientsPhone = recipientsPhone,
                                        recipientsAddress = recipientsAddress,
                                        recipientsPostcode = recipientsPostcode)
          await(saveToMongo(userId, address))
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
        logger.error(s"UpdateUserAddress error: ${error}")
        val header = ResponseHeader(ResultCode.INTERNAL_SERVER_ERROR, error.toString)
        replyPromise success UpdateUserAddressResponse().withHeader(header)
    }

    // send response
    replyPromise.future
  }
}