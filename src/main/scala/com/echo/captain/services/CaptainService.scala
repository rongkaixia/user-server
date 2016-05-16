package com.echo.captain

import scala.async.Async.{async, await}
import scala.concurrent.duration._
import scala.concurrent.{ Future, Promise }
import scala.concurrent._
import scala.util.{Success, Failure}
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import java.util.UUID

import akka.actor._
import akka.pattern.ask
import akka.pattern.pipe
import akka.util.Timeout
import akka.event.Logging

import spray.routing._
import spray.http.HttpData
import spray.json._

import com.datastax.driver.core._
import com.trueaccord.scalapb.json.JsonFormat
import com.google.common.util.concurrent.{FutureCallback, Futures}

import com.echo.protocol.{Request, Response}
import com.echo.common._

class CaptainService() extends Actor with akka.actor.ActorLogging{
  import context.dispatcher // ExecutionContext for the futures and scheduler

  val cfg = context.system.settings.config

  // cassandra connection
  var client: Option[CassandraClient] = None

  override def preStart(): Unit = {
    client = Some(CassandraClient(cfg.getConfig("echo.captain.cassandra.system")))
    client.get.connect
    initilize
  }

  override def postRestart(reason: Throwable): Unit = {
    preStart()
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    context.children foreach { child ⇒
      context.unwatch(child)
      context.stop(child)
    }
    postStop()
  }

  override def postStop(): Unit = {
    if (client.isDefined){
      client.get.shutdown
      client = None
    }
  }

  def initilize(): Unit = {
  }

  // ===========begin main function=============
  def isPhoneNumExisted(phonenum: String): Future[Boolean] = {
    // checking whether the phonenum is existed
    val promise = Promise[Boolean]()
    try{
      val session = client.get.getSession
      val tableName = cfg.getString("echo.captain.cassandra.user_by_phonenum_table")
      val phoneColumn = cfg.getString("echo.captain.cassandra.columns.phonenum")

      val queryPhoneString = "SELECT " + phoneColumn + " FROM " +
        tableName + " WHERE " + phoneColumn + "='" + phonenum + "'"
      log.info("checking whether the phonenum is existed")
      log.debug("queryPhoneString: " + queryPhoneString)

      val phoneFuture = session.executeAsync(queryPhoneString)
      Futures.addCallback(phoneFuture, new FutureCallback[ResultSet](){
        override def onSuccess(res: ResultSet): Unit = {
          if (res.all.asScala.toList.length > 0)
            promise success true
          else
            promise success false
        }
        override def onFailure(error: Throwable): Unit = {
          log.error("query phonenum error: " + error + ", queryPhoneString = " + queryPhoneString)
          promise failure error
        }
      })
    }catch{
      case error: Throwable => promise failure error
    }
    promise.future
  }

  def insertUserByTable(tableName: String, keyColumn: String, key: String, id: UUID): Future[Unit] = {
    val promise = Promise[Unit]()
    try{
      log.info("insert into table " + tableName)
      val session = client.get.getSession
      val insertString = "INSERT INTO " + tableName + "(" + keyColumn + ", id) VALUES(?,?)"
      val statement = session.prepare(insertString)
      log.debug("insertString: " + insertString)
      val boundStatement = new BoundStatement(statement).setString(0, key)
                                                        .setUUID(1, id)
      val insertFuture = session.executeAsync(boundStatement)
      Futures.addCallback(insertFuture, new FutureCallback[ResultSet](){
        override def onSuccess(res: ResultSet): Unit = {
          log.info("insert into table " + tableName + " success")
          promise.success()
        }
        override def onFailure(error: Throwable): Unit = {
          log.error("insert into table " + tableName + " error: " + error)
          promise failure error
        }
      })
    }catch{
      case error: Throwable => promise failure error
    }
    promise.future
  }

  def handleSignupRequest(req: Request.SignupRequest): Future[Response] = {
    val promise = Promise[Response]()
    val session = client.get.getSession
    var response = new Response()
    // check request
    val phonenum = req.phonenum
    val password = req.password
    if (phonenum.isEmpty){
      promise.success(response.withResult(Response.ResultCode.FAIL)
                              .withErrorDescription("phonenum cannot be empty.")
                              .withSignupResponse(new Response.SignupResponse()))
    }else if(password.isEmpty){
      promise.success(response.withResult(Response.ResultCode.FAIL)
                              .withErrorDescription("password cannot be empty.")
                              .withSignupResponse(new Response.SignupResponse()))
    }else{
      try{
        val userInfoTable = cfg.getString("echo.captain.cassandra.user_info_table")
        val userByPhonenumTable = cfg.getString("echo.captain.cassandra.user_by_phonenum_table")
        val userByUsernameTable = cfg.getString("echo.captain.cassandra.user_by_username_table")
        val phoneColumn = cfg.getString("echo.captain.cassandra.columns.phonenum")
        // insert statement
        val insertString = "INSERT INTO " + userInfoTable + "(id, phonenum, password) VALUES(?,?,?)"
        val statement = session.prepare(insertString)
        log.debug("insertString: " + insertString)
        // async operation
        val f = async{
          // checking whether the phonenum is existed
          val isExisted = await(isPhoneNumExisted(phonenum))
          log.info("isPhoneNumExisted: " + isExisted)
          if (isExisted){
            promise.success(response.withResult(Response.ResultCode.FAIL)
                                    .withErrorDescription("phonenum[" + phonenum + "] already existed.")
                                    .withSignupResponse(new Response.SignupResponse()))
          }else{
            val id = UUID.randomUUID
            await(insertUserByTable(userByPhonenumTable, phoneColumn, phonenum, id))
            log.info("insert new user into db")
            log.debug("insertString: " + insertString)
            val boundStatement = new BoundStatement(statement).setUUID(0, id)
                                                              .setString(1, phonenum)
                                                              .setString(2, password)
            val insertFuture = session.executeAsync(boundStatement)
            Futures.addCallback(insertFuture, new FutureCallback[ResultSet](){
              override def onSuccess(res: ResultSet): Unit = {
                log.info("insert new user success")
                val signupResponse = new Response.SignupResponse()
                promise.success(response.withResult(Response.ResultCode.SUCCESS)
                                        .withErrorDescription("OK")
                                        .withSignupResponse(signupResponse))
              }
              override def onFailure(error: Throwable): Unit = {
                log.error("insert new user error: " + error + ", " + "insertString = " + insertString)
                promise failure error
              }
            })
          }
        }//async
        // in case of async{...} throw exception
        f onFailure {
          case error: Throwable => 
            log.error("async{...} error: " + error)
            promise failure error
        }
      }catch{
        case error: Throwable =>
          log.error("handleSignupRequest Exception: " + error)
          promise failure error
      }
    }

    promise.future
  }
  // ===========end main function===============
  def receive = {
    case req: Request.LoginRequest => {

      log.info("receive login request: " + req.toString)
    }
    case req: Request.SignupRequest => {
      log.info("receive signup request: " + req.toString)
      handleSignupRequest(req) pipeTo sender
    }//signup request
  }//receive
}