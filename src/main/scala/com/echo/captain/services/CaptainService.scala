package com.echo.captain

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.concurrent.{ExecutionContext, Future}
import scala.async.Async.{async, await}

import com.typesafe.scalalogging.{Logger, LazyLogging}
import com.typesafe.config.{Config, ConfigFactory}
import com.trueaccord.scalapb.json.JsonFormat

import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Updates._
import com.mongodb.connection.ClusterSettings
import com.echo.protocol.captain._
import com.echo.protocol.common._

class CaptainServiceException(message: String = null, cause: Throwable = null) extends
  RuntimeException(CaptainServiceException.defaultMessage(message, cause), cause)

object CaptainServiceException {
  def defaultMessage(message: String, cause: Throwable) = {
    if (message != null) message
    else if (cause != null) cause.toString()
    else null
  }

  class OrderNotExist(id: String) extends CaptainServiceException(s"order not existed for orderId[${id}]")
}


trait AbstractCaptainService extends CaptainServiceGrpc.CaptainService with LazyLogging{
  implicit val ec: ExecutionContext
  val mongo: MongoClient
  val cfg: Config
  
  def isPhonenumExisted(phonenum: String): Future[Boolean] = {
    async{
      val dbName = cfg.getString("echo.captain.mongo.user.db")
      val collectionName = cfg.getString("echo.captain.mongo.user.collection")
      val phonenumColumn = cfg.getString("echo.captain.mongo.user.columns.phonenum")
      val database: MongoDatabase = mongo.getDatabase(dbName)
      val collection = database.getCollection(collectionName)

      val filterOp = equal(phonenumColumn, phonenum)
      val projectionOp = exclude("_id")
      val result = await(collection.find(filterOp).projection(projectionOp).first().toFuture)
      if (result.size >= 1) {
        true
      }else {
        false
      }
    }
  }

  /**
   * [oauthToken description]
   *
   * @expiresIn   Int    expires in second
   */
  def saveAuthToken(authToken: String,
                    authName: String, 
                    authID: String,
                    userID: String,
                    username: String,
                    expiresIn: Int): Future[Unit] = {
    async{
      val dbName = cfg.getString("echo.captain.mongo.auth.db")
      val collectionName = cfg.getString("echo.captain.mongo.auth.collection")
      val userIdColumn = cfg.getString("echo.captain.mongo.auth.columns.user_id")
      val usernameColumn = cfg.getString("echo.captain.mongo.auth.columns.username")
      val authTokenColumn = cfg.getString("echo.captain.mongo.auth.columns.auth_access_token")
      val authNameColumn = cfg.getString("echo.captain.mongo.auth.columns.auth_name")
      val authIDColumn = cfg.getString("echo.captain.mongo.auth.columns.auth_id")
      val createAtColumn = cfg.getString("echo.captain.mongo.auth.columns.create_at")
      val updateAtColumn = cfg.getString("echo.captain.mongo.auth.columns.update_at")
      val expireAtColumn = cfg.getString("echo.captain.mongo.auth.columns.expire_at")

      val database: MongoDatabase = mongo.getDatabase(dbName)
      val collection = database.getCollection(collectionName)
      val createAt = java.time.Instant.now.toEpochMilli
      val updateAt = createAt
      val expireAt = createAt + expiresIn * 1000

      val doc = Document(authTokenColumn -> authToken,
                         authNameColumn -> authName,
                         authIDColumn -> authID,
                         userIdColumn -> userID,
                         usernameColumn -> username,
                         createAtColumn -> new bson.BsonDateTime(createAt),
                         updateAtColumn -> new bson.BsonDateTime(updateAt),
                         expireAtColumn -> new bson.BsonDateTime(expireAt))
      logger.debug(s"refreshToken: Document=${doc}")
      await(collection.insertOne(doc).toFuture)
    }
  }

  /**
   * @return  Map of auth collection
   */
  def queryAuth(authToken: String): Future[Map[String, bson.BsonValue]] = {
    async{
      val dbName = cfg.getString("echo.captain.mongo.auth.db")
      val collectionName = cfg.getString("echo.captain.mongo.auth.collection")
      val authTokenColumn = cfg.getString("echo.captain.mongo.auth.columns.auth_access_token")

      val database: MongoDatabase = mongo.getDatabase(dbName)
      val collection = database.getCollection(collectionName)

      val filterOp = equal(authTokenColumn, authToken)
      val projectionOp = exclude("_id")
      val result = await(collection.find(filterOp).projection(projectionOp).first().toFuture)
      if (result.size >= 1) {
        result.head.toMap
      }else {
        Map[String, bson.BsonValue]()
      }
    }
  }
}

class CaptainService() 
  extends SignupImpl
  with LoginImpl
  with LogoutImpl
  with AuthImpl{

  // execution context
  val ec = ExecutionContext.Implicits.global
  // config
  val cfg = ConfigFactory.load()
  // init mongodb
  val mongo = initMongo
  
  private def initMongo(): MongoClient = {
    val host = cfg.getString("echo.captain.mongo.host")
    val port = cfg.getInt("echo.captain.mongo.port")
    logger.info(s"mongodb[host=${host}, port=${port}")
    val clusterSettings: ClusterSettings = 
      ClusterSettings.builder().hosts(List(new ServerAddress(host, port)).asJava).build()
    val settings: MongoClientSettings = 
      MongoClientSettings.builder().clusterSettings(clusterSettings).build()
    MongoClient(settings)
  }
}