package com.echo.captain

import scala.async.Async.{async, await}
import scala.concurrent.duration._
import scala.concurrent.{ Future, Promise }
import scala.concurrent._
import scala.util.{Success, Failure}
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import java.util.UUID
import java.time.Instant

import akka.actor._
import akka.pattern.pipe
import akka.event.Logging

import spray.routing._
import spray.json._

import com.datastax.driver.core._

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.impl.crypto.MacProvider

import org.json4s.native.Json
import org.json4s.DefaultFormats

import com.echo.protocol.{Request, Response, LoginType, AuthType}
import com.echo.common._
import com.echo.common.Utils._

class CaptainService() extends Actor with akka.actor.ActorLogging{
  import context.dispatcher // ExecutionContext for the futures and scheduler

  val cfg = context.system.settings.config
  val jwtSecretKey = cfg.getString("echo.captain.jwt_secret_key")
  val tokenExpiresIn = cfg.getInt("echo.captain.token_expires_in")

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

  def addPrefixToToken(token: String, tp: LoginType): String = {
    tp match{
      case LoginType.LOGIN_BY_WECHAT => "wechat_" + token
      case LoginType.LOGIN_BY_WEIBO => "weibo_" + token
      case _ => "local_" + token
    }
  } 

  // ===========begin main function=============
  def isPhoneNumExisted(phonenum: String): Future[Boolean] = {
    // checking whether the phonenum is existed
    val promise = Promise[Boolean]()
    try{
      val session = client.get.getSession
      val tableName = cfg.getString("echo.captain.cassandra.user_tables.user_by_phonenum_table")
      val phoneColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.phonenum")

      val queryPhoneString = "SELECT " + phoneColumn + " FROM " +
        tableName + " WHERE " + phoneColumn + "='" + phonenum + "'"
      log.info("checking whether the phonenum is existed")
      log.debug("queryPhoneString: " + queryPhoneString)

      val phoneFuture = session.executeAsync(queryPhoneString).toScalaFuture
      phoneFuture onComplete {
        case Success(res: ResultSet) =>
          if (res.all.asScala.toList.length > 0)
            promise success true
          else
            promise success false
        case Failure(error: Throwable) => 
          log.error("query phonenum error: " + error + ", queryPhoneString = " + queryPhoneString)
          promise failure error
      }
    }catch{
      case error: Throwable => promise failure error
    }
    promise.future
  }

  def insertUserByTable(
    tableName: String, 
    keyColumn: String, 
    key: String, 
    id: UUID,
    password: String): Future[Unit] = {
    val promise = Promise[Unit]()
    try{
      log.info("insert into table " + tableName)
      val session = client.get.getSession
      val userIDColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.user_id")
      val passwordColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.password")
      val insertString = "INSERT INTO " + tableName + "(" + keyColumn + "," + 
        userIDColumn + "," + passwordColumn +  ") VALUES(?,?,?)"
      val statement = session.prepare(insertString)
      log.debug("insertString: " + insertString)
      val boundStatement = new BoundStatement(statement).setString(0, key)
                                                        .setUUID(1, id)
                                                        .setString(2, password)
      val insertFuture = session.executeAsync(boundStatement).toScalaFuture
      insertFuture onComplete {
        case Success(res: ResultSet) =>
          log.info("insert into table " + tableName + " success")
          promise.success()
        case Failure(error: Throwable) =>
          log.error("insert into table " + tableName + " error: " + error)
          promise failure error
      }
    }catch{
      case error: Throwable => promise failure error
    }
    promise.future
  }

  def handleSignupRequest(req: Request.SignupRequest): Future[Response] = {
    val promise = Promise[Response]()
    try{
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
        val userInfoTable = cfg.getString("echo.captain.cassandra.user_tables.user_info_table")
        val userByPhonenumTable = cfg.getString("echo.captain.cassandra.user_tables.user_by_phonenum_table")
        val userByUsernameTable = cfg.getString("echo.captain.cassandra.user_tables.user_by_username_table")
        val phoneColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.phonenum")
        val userIDColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.user_id")
        val passwordColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.password")
        // insert statement
        val insertString = "INSERT INTO " + userInfoTable + "(" +
          userIDColumn + "," + phoneColumn + "," + passwordColumn + ") " +
          "VALUES(?,?,?)"
        log.debug("insertString: " + insertString)
        val statement = session.prepare(insertString)
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
            await(insertUserByTable(userByPhonenumTable, phoneColumn, phonenum, id, password))
            log.info("insert new user into db")
            log.debug("insertString: " + insertString)
            val boundStatement = new BoundStatement(statement).setUUID(0, id)
                                                              .setString(1, phonenum)
                                                              .setString(2, password)
            val insertFuture = session.executeAsync(boundStatement).toScalaFuture
            insertFuture onComplete {
              case Success(res: ResultSet) =>
                log.info("insert new user success")
                val signupResponse = new Response.SignupResponse()
                promise.success(response.withResult(Response.ResultCode.SUCCESS)
                                        .withErrorDescription("OK")
                                        .withSignupResponse(signupResponse))
              case Failure(error: Throwable) =>
                log.error("insert new user error: " + error + ", " + "insertString = " + insertString)
                promise failure error
            }
          }
        }//async
        // in case of async{...} throw exception
        f onFailure {
          case error: Throwable => 
            log.error("handleSignupRequest async{...} error: " + error)
            promise failure error
        }
      }
    }catch{
      case error: Throwable =>
        log.error("handleSignupRequest Exception: " + error)
        promise failure error
    }

    promise.future
  }

  def isUserExisted(tableName: String, keyColumn: String, key: String): Future[Boolean] = {
    val promise = Promise[Boolean]()
    try{
      log.info("check username by " + tableName)
      val session = client.get.getSession
      val userIDColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.user_id")
      val passwordColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.password")
      val queryString = "SELECT count(*) FROM " + tableName + " WHERE " + keyColumn + "=?"
      val statement = session.prepare(queryString)
      log.debug("queryString: " + queryString)
      val boundStatement = new BoundStatement(statement).setString(0, key)
      val f = session.executeAsync(boundStatement).toScalaFuture
      f onComplete {
        case Success(res: ResultSet) =>
          log.info("query table " + tableName + " success")
          if (res.all.asScala.toList.length > 0)
            promise success true
          else 
            promise success false
        case Failure(error: Throwable) =>
          log.error("query table " + tableName + " error: " + error)
          promise failure error
      }
    }catch{
      case error: Throwable => promise failure error
    }
    promise.future
  }

  /**
   * @return  (isCorrected: Boolean, userID: UUID)
   */
  def isPasswordCorrected(
    tableName: String, 
    keyColumn: String, 
    key: String,
    password: String): Future[(Boolean, UUID)] = {
    val promise = Promise[(Boolean, UUID)]()
    try{
      log.info("check password by " + tableName)
      val session = client.get.getSession
      val userIDColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.user_id")
      val passwordColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.password")
      val queryString = "SELECT " + userIDColumn + ", " + passwordColumn +
        " FROM " + tableName + " WHERE " + keyColumn + "=?"
      val statement = session.prepare(queryString)
      log.debug("queryString: " + queryString)
      val boundStatement = new BoundStatement(statement).setString(0, key)
      val f = session.executeAsync(boundStatement).toScalaFuture
      f onComplete {
        case Success(res: ResultSet) =>
          log.info("query table " + tableName + " success")
          val result = res.all.asScala.toList
          if (result.length >0){
            val row = result.head
            val id = row.getUUID(userIDColumn)
            val expectedPassword = row.getString(passwordColumn)
            log.debug("user(id, password): " + "(" + id + "," + expectedPassword + ")")
            if (expectedPassword != password){
              log.info("password error: expected password is " + expectedPassword + " not " + password)
              promise.success((false, UUID.randomUUID))
            }else{
              promise.success((true, id))
            }
          }else{
            promise failure new NoSuchElementException("user " + key + " not existed")
          }
        case Failure(error: Throwable) =>
          log.error("query table " + tableName + " error: " + error)
          promise failure error
      }
    }catch{
      case error: Throwable => promise failure error
    }
    promise.future
  }

  /**
   * [oauthToken description]
   *
   * @expiresIn   Int    expires in second
   */
  def setTokenExpires(
    authToken: String, 
    authName: AuthType, 
    authID: String,
    userID: UUID,
    expiresIn: Int): Future[Unit] = {
    val promise = Promise[Unit]()
    try{
      log.debug("set token expires")
      val expires = Instant.now.plusSeconds(expiresIn)
      log.info("token[" + authToken + "] expires at " + expires.toString)
      val session = client.get.getSession
      val tableName = cfg.getString("echo.captain.cassandra.auth_tables.auth_table")
      val uniqueIDColumn = cfg.getString("echo.captain.cassandra.auth_tables.columns.id")
      val tokenColumn = cfg.getString("echo.captain.cassandra.auth_tables.columns.auth_access_token")
      val authNameColumn = cfg.getString("echo.captain.cassandra.auth_tables.columns.auth_name")
      val authIDColumn = cfg.getString("echo.captain.cassandra.auth_tables.columns.auth_id")
      val userIDColumn = cfg.getString("echo.captain.cassandra.auth_tables.columns.user_id")
      val authExpiresColumn = cfg.getString("echo.captain.cassandra.auth_tables.columns.auth_expires")

      val insertString = "INSERT INTO " + tableName + "(" +
        Array(tokenColumn, authNameColumn, authIDColumn, userIDColumn, authExpiresColumn)
        .reduce((a,b) => a + "," + b) + ") " +
        "VALUES(?,?,?,?,?) USING TTL " + expiresIn
      val statement = session.prepare(insertString)
      log.debug("insertString: " + insertString)
      val id = UUID.randomUUID
      val boundStatement = new BoundStatement(statement).setString(0, authToken)
                                                        .setString(1, authName.toString)
                                                        .setString(2, authID)
                                                        .setUUID(3, userID)
                                                        .setTimestamp(4, java.util.Date.from(expires))
      val f = session.executeAsync(boundStatement).toScalaFuture
      f onComplete {
        case Success(res: ResultSet) =>
          log.info("query table " + tableName + " success")
          promise.success()
        case Failure(error: Throwable) =>
          log.error("query table " + tableName + " error: " + error)
          promise failure error
      }
    }catch{
      case error: Throwable =>
        log.error("setTokenExpires error: " + error)
        promise failure error      
    }
    promise.future
  }

  def handleLoginRequest(req: Request.LoginRequest): Future[Response] = {
    val promise = Promise[Response]()
    try{
      val session = client.get.getSession
      var response = new Response()
      // check request
      val password = req.password
      val (loginType, name: String) = {
        if (req.name.isPhonenum)
          (LoginType.LOGIN_BY_PHONENUM, req.getPhonenum)
        else if(req.name.isUsername)
          (LoginType.LOGIN_BY_USERNAME, req.getUsername)
        else if(req.name.isEmail)
          (LoginType.LOGIN_BY_EMAIL, req.getEmail)
        else
          (LoginType.LOGIN_TYPE_EMPTY, "")
      }
      if (loginType == LoginType.LOGIN_TYPE_EMPTY || name.isEmpty){
          promise.success(response.withResult(Response.ResultCode.FAIL)
                                  .withErrorDescription("name cannot be empty.")
                                  .withLoginResponse(new Response.LoginResponse()))
      }
      else if(password.isEmpty){
        promise.success(response.withResult(Response.ResultCode.FAIL)
                                .withErrorDescription("password cannot be empty.")
                                .withLoginResponse(new Response.LoginResponse()))
      }else{
        val f = async {
          val userByPhonenumTable = cfg.getString("echo.captain.cassandra.user_tables.user_by_phonenum_table")
          val userByUsernameTable = cfg.getString("echo.captain.cassandra.user_tables.user_by_username_table")
          val userByEmailTable = cfg.getString("echo.captain.cassandra.user_tables.user_by_email_table")
          val phoneColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.phonenum")
          val usernameColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.username")
          val emailColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.email")
          val userExisted: Boolean = loginType match {
            case LoginType.LOGIN_BY_PHONENUM => 
              await(isUserExisted(userByPhonenumTable, phoneColumn, name))
            case LoginType.LOGIN_BY_USERNAME => 
              await(isUserExisted(userByUsernameTable, usernameColumn, name))
            case LoginType.LOGIN_BY_EMAIL => 
              await(isUserExisted(userByEmailTable, emailColumn, name))
            case _ => 
              log.error("loginType error, there is a critical server error if this message show up")
              true
          }
          log.info("isUserExisted: " + userExisted)
          if (!userExisted){
            promise.success(response.withResult(Response.ResultCode.FAIL)
                                    .withErrorDescription("user " + name + " not existed.")
                                    .withLoginResponse(new Response.LoginResponse()))
          }else{
            val (passwordCorrected: Boolean, userID: UUID) = loginType match {
              case LoginType.LOGIN_BY_PHONENUM => 
                await(isPasswordCorrected(userByPhonenumTable, phoneColumn, name, password))
              case LoginType.LOGIN_BY_USERNAME => 
                await(isPasswordCorrected(userByUsernameTable, usernameColumn, name, password))
              case LoginType.LOGIN_BY_EMAIL => 
                await(isPasswordCorrected(userByEmailTable, emailColumn, name, password))
              case _ => 
                log.error("loginType error, there is a critical server error if this message show up")
                false
            }
            log.info("isPasswordCorrected: " + passwordCorrected)
            if (!passwordCorrected){
              promise.success(response.withResult(Response.ResultCode.FAIL)
                                      .withErrorDescription("password incorrected.")
                                      .withLoginResponse(new Response.LoginResponse()))
            }else{
              log.info("generate Json Web Token with " + userID.toString + "...")
              val token = addPrefixToToken(Jwts.builder()
                                               .setSubject(userID.toString)
                                               .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                                               .compact(), loginType)

              log.info("token: " + token)
              await(setTokenExpires(token, AuthType.LOCAL, "", userID, tokenExpiresIn))
              val loginRes = new Response.LoginResponse()
                                         .withToken(token)
                                         .withExpiresIn(tokenExpiresIn)
                                         .withUserId(userID.toString)
              promise.success(response.withResult(Response.ResultCode.SUCCESS)
                                      .withLoginResponse(loginRes))
            }
          }
        }//async
        // in case of async{...} throw exception
        f onFailure {
          case error: Throwable => 
            log.error("handleLoginRequest async{...} error: " + error)
            promise failure error
        }
      }
    }catch{
      case error: Throwable =>
        log.error("handleLoginRequest Exception: " + error)
        promise failure error
    }
    promise.future
  }

  /**
   * @return  (isExpired: Boolean, userID: UUID, expiresIn: Int)
   */
  def isTokenExpired(token: String): Future[(Boolean, UUID, Int)] = {
    val promise = Promise[(Boolean, UUID, Int)]()
    try{
      log.info("checking whether token is expired, token is " + token)
      val session = client.get.getSession
      val tableName = cfg.getString("echo.captain.cassandra.auth_tables.auth_table")
      val tokenColumn = cfg.getString("echo.captain.cassandra.auth_tables.columns.auth_access_token")
      val authNameColumn = cfg.getString("echo.captain.cassandra.auth_tables.columns.auth_name")
      val userIDColumn = cfg.getString("echo.captain.cassandra.auth_tables.columns.user_id")

      val queryString = "SELECT " + userIDColumn + ", ttl(" + userIDColumn + ") FROM " + tableName +
        " WHERE " + tokenColumn + "=?"
      log.debug("queryString: " + queryString)
      val statement = session.prepare(queryString)
      val boundStatement = new BoundStatement(statement).setString(0, token)
      val f = session.executeAsync(boundStatement).toScalaFuture
      f onComplete {
        case Success(res: ResultSet) =>
          log.info("query table " + tableName + " success")
          val result = res.all.asScala.toList
          if (result.length >0){
            val row = result.head
            val userID = row.getUUID(userIDColumn)
            val expiresIn = row.getInt(1)
            promise.success((false, userID, expiresIn))
          }else{
            promise.success((true, UUID.randomUUID, 0))
          }
        case Failure(error: Throwable) =>
          log.error("query table " + tableName + " error: " + error)
          promise failure error
      }
    }catch{
      case error: Throwable =>
        promise failure error
    }
    promise.future
  }

  def refreshToken(token: String): Future[Unit] = {
    val promise = Promise[Unit]()
    try{

    }catch{
      case error: Throwable =>
        promise failure error
    }
    promise.future
  }

  def handleAuthenticationRequest(req: Request.AuthenticationRequest): Future[Response] = {
    val promise = Promise[Response]()
    try{
      val session = client.get.getSession
      var response = new Response()
      // check request
      val token: String = req.token
      if (token.isEmpty){
        promise.success(response.withResult(Response.ResultCode.FAIL)
                                .withErrorDescription("token cannot be empty.")
                                .withAuthenticationResponse(new Response.AuthenticationResponse()))
      }else{
        val f = async{
          val (isExpired: Boolean, userID: UUID, expiresIn: Int) = await(isTokenExpired(token))
          val authRes = if(isExpired){
            log.info("token[" + token + "] is expired")
            new Response.AuthenticationResponse()
                        .withIsExpired(isExpired)
                        .withExpiresIn(0)
          }else{
            log.info("user had logon, userID = " + userID.toString + 
              ", expires in " + expiresIn + "seconds.")
            new Response.AuthenticationResponse()
                        .withIsExpired(isExpired)
                        .withExpiresIn(expiresIn)
                        .withUserId(userID.toString)
          }
          promise.success(response.withResult(Response.ResultCode.SUCCESS)
                                  .withAuthenticationResponse(authRes))
        }//async
        // in case of async{...} throw exception
        f onFailure {
          case error: Throwable => 
            log.error("handleLoginRequest async{...} error: " + error)
            promise failure error
        }
      }
    }catch{
      case error: Throwable =>
        log.error("handleAuthenticationRequest Exception: " + error)
        promise failure error
    }
    promise.future
  }


  // ===========end main function===============
  def receive = {
    case req: Request.SignupRequest => {
      log.info("receive signup request: " + req.toString)
      handleSignupRequest(req) pipeTo sender
    }
    case req: Request.LoginRequest => {
      log.info("receive login request: " + req.toString)
      handleLoginRequest(req) pipeTo sender
    }
    case req: Request.AuthenticationRequest => {
      log.info("receive authentication request: " + req.toString)
      handleAuthenticationRequest(req) pipeTo sender
    }
  }//receive
}