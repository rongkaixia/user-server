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

import com.echo.protocol._
import com.echo.common._
import com.echo.common.CassandraClient._

class CaptainService() extends Actor with akka.actor.ActorLogging{
  import context.dispatcher // ExecutionContext for the futures and scheduler

  val cfg = context.system.settings.config
  val jwtSecretKey = cfg.getString("echo.captain.jwt_secret_key")
  val tokenExpiresIn = cfg.getInt("echo.captain.token_expires_in")

  // cassandra user info table config
  val userInfoTable = cfg.getString("echo.captain.cassandra.user_tables.user_info_table")
  val userIDColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.user_id")
  val usernameColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.username")
  val passwordColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.password")
  val phoneColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.phonenum")
  val emailColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.email")
  val secQues1Column = cfg.getString("echo.captain.cassandra.user_tables.columns.security_question1")
  val secQues2Column = cfg.getString("echo.captain.cassandra.user_tables.columns.security_question2")
  val secQues3Column = cfg.getString("echo.captain.cassandra.user_tables.columns.security_question3")
  val secQues1AnsColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.security_question1_ans")
  val secQues2AnsColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.security_question2_ans")
  val secQues3AnsColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.security_question3_ans")
  val createdTimeColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.created")
  val lastModifiedColumn = cfg.getString("echo.captain.cassandra.user_tables.columns.last_modified")

  // cassandra auth table config
  val authTable = cfg.getString("echo.captain.cassandra.auth_tables.auth_table")
  val authNameColumn = cfg.getString("echo.captain.cassandra.auth_tables.columns.auth_name")
  val authIDColumn = cfg.getString("echo.captain.cassandra.auth_tables.columns.auth_id")
  val authUserIDColumn = cfg.getString("echo.captain.cassandra.auth_tables.columns.user_id")
  val authUsernameColumn = cfg.getString("echo.captain.cassandra.auth_tables.columns.username")
  val authTokenColumn = cfg.getString("echo.captain.cassandra.auth_tables.columns.auth_access_token")
  val authExpiresColumn = cfg.getString("echo.captain.cassandra.auth_tables.columns.auth_expires")

  // cassandra address table config
  val addressTable = cfg.getString("echo.captain.cassandra.address_tables.address_table")
  val addressIDColumn = cfg.getString("echo.captain.cassandra.address_tables.columns.address_id")
  val addressUserIDColumn = cfg.getString("echo.captain.cassandra.address_tables.columns.user_id")
  val recipientsNameColumn = cfg.getString("echo.captain.cassandra.address_tables.columns.recipients_name")
  val recipientsPhoneColumn = cfg.getString("echo.captain.cassandra.address_tables.columns.recipients_phone")
  val recipientsAddressColumn = cfg.getString("echo.captain.cassandra.address_tables.columns.recipients_address")
  val recipientsPostCodeColumn = cfg.getString("echo.captain.cassandra.address_tables.columns.recipients_postcode")

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


  def generateToken(subject: String): String = {
    Jwts.builder()
    .setSubject(subject)
    .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
    .compact()
  }

  def addPrefixToToken(token: String, tp: LoginType = null): String = {
    tp match{
      case LoginType.LOGIN_BY_WECHAT => "wechat_" + token
      case LoginType.LOGIN_BY_WEIBO => "weibo_" + token
      case _ => "local_" + token
    }
  } 

  // ===========begin main function=============
  def getUserAddresses(id: String): Future[Seq[UserAddress]] = {
    async{
      log.debug("getUserAddresses")
      val session = client.get.getSession
      val queryString = "select * from " + addressTable + " WHERE " +
                        addressUserIDColumn + "=? ALLOW FILTERING"
      log.debug("queryString: " + queryString)
      val statement = session.prepare(queryString)
      val boundStatement = new BoundStatement(statement).setString(0, id)
      val res = await(session.executeAsync(boundStatement).toScalaFuture)
      res.all.asScala.toList.map(row => {
        val id = row.getString(addressIDColumn)
        val name = row.getString(recipientsNameColumn)
        val phonenum = row.getString(recipientsPhoneColumn)
        val address = row.getString(recipientsAddressColumn)
        var addressData = new UserAddress()
        if (id != null) addressData = addressData.withId(id)
        if (name != null) addressData = addressData.withRecipientsName(name)
        if (phonenum != null) addressData = addressData.withRecipientsPhone(phonenum)
        if (address != null) addressData = addressData.withRecipientsAddress(address)
        addressData
      }).toSeq
    }
  }
  def getUserInfo(id: String): Future[UserInfo] = {
    async{
      log.info("getUserInfo")
      val session = client.get.getSession
      val queryString = "SELECT * FROM " + userInfoTable + " WHERE " + 
        userIDColumn + "=?"
      val statement = session.prepare(queryString)
      log.debug("queryString: " + queryString)
      val boundStatement = new BoundStatement(statement).setString(0, id)
      val res = await(session.executeAsync(boundStatement).toScalaFuture)
      val row = res.all.asScala.toList.head
      val addresses = await(getUserAddresses(id))
      var securityQuestion1: SecurityQuestionPair = null
      var securityQuestion2: SecurityQuestionPair = null
      var securityQuestion3: SecurityQuestionPair = null
      if (row.getString(secQues1Column) != null && row.getString(secQues1AnsColumn) != null)
        securityQuestion1 = new SecurityQuestionPair().withQuestion(row.getString(secQues1Column))
                                                      .withAnswer(row.getString(secQues1AnsColumn))
      if (row.getString(secQues2Column) != null && row.getString(secQues2AnsColumn) != null)
        securityQuestion2= new SecurityQuestionPair().withQuestion(row.getString(secQues2Column))
                                                      .withAnswer(row.getString(secQues2AnsColumn))
      if (row.getString(secQues3Column) != null && row.getString(secQues3AnsColumn) != null)
        securityQuestion3 = new SecurityQuestionPair().withQuestion(row.getString(secQues3Column))
                                                      .withAnswer(row.getString(secQues3AnsColumn))
      new UserInfo(id = id, 
                   username = row.getString(usernameColumn),
                   email = row.getString(emailColumn),
                   phonenum = row.getString(phoneColumn),
                   securityQuestion1 = securityQuestion1,
                   securityQuestion2 = securityQuestion2,
                   securityQuestion3 = securityQuestion3,
                   addresses = addresses)
    }
  }

  def updateUserInfo(userID: String, kvs: Seq[(String, String)]): Future[Unit] = {
    async {
      val allowedKey = Array(usernameColumn, phoneColumn, passwordColumn, emailColumn)
      // check kvs
      // if (!allowedKey.contains(key)) {
      //   throw new IllegalArgumentException("updateUserInfo key [" + key + "] not allowed")
      // }
      val session = client.get.getSession
      val updateString =  "UPDATE " + userInfoTable + " SET " + 
                          kvs.map(_._1).map(ele => ele.toString + "=?").reduce((a, b) => a + ", " + b) +
                          " WHERE " + userIDColumn + " = ?"
      log.debug("updateString: " + updateString)
      val statement = session.prepare(updateString)
      var boundStatement = new BoundStatement(statement).setString(userIDColumn, userID)
      kvs.foreach(kv => {
        boundStatement = boundStatement.setString(kv._1, kv._2)
      })
      await(session.executeAsync(boundStatement).toScalaFuture)
      log.debug("updateUserInfo " + kvs + " success")
    }
  }

  def isUserExisted(tableName: String, keyColumn: String, key: String): Future[Boolean] = {
    async{
      log.info("check username by " + tableName)
      val session = client.get.getSession
      val queryString = "SELECT * FROM " + tableName + " WHERE " + keyColumn + "=?"
      log.debug("queryString: " + queryString)
      val statement = session.prepare(queryString)
      val boundStatement = new BoundStatement(statement).setString(0, key)
      val res = await(session.executeAsync(boundStatement).toScalaFuture)
      log.info("query table " + tableName + " success")
      if (res.all.asScala.toList.length > 0) {
        true
      } else {
        false
      }
    }
  }

  def handleSignupRequest(req: Request.SignupRequest): Future[Response] = {
    val future = async{
      val session = client.get.getSession
      var response = new Response()
      // check request
      val phonenum = req.phonenum
      val password = req.password
      if (phonenum.isEmpty){
        response.withResult(ResultCode.INVALID_PHONENUM)
                .withErrorDescription("phonenum cannot be empty.")
                .withSignupResponse(new Response.SignupResponse())
      }else if(password.isEmpty){
        response.withResult(ResultCode.INVALID_PASSWORD)
                .withErrorDescription("password cannot be empty.")
                .withSignupResponse(new Response.SignupResponse())
      }else{

        // checking whether the phonenum is existed
        val isExisted = await(isUserExisted(userInfoTable, phoneColumn, phonenum))
        log.info("isPhoneNumExisted: " + isExisted)
        if (isExisted){
          response.withResult(ResultCode.PHONENUM_ALREADY_EXISTED)
                  .withErrorDescription("phonenum[" + phonenum + "] already existed.")
                  .withSignupResponse(new Response.SignupResponse())
        }else{
          // insert user_info table
          val userID = UUID.randomUUID().toString // using random uuid as id
          val username = phonenum
          log.info("inserting new user into user_info table")
          val insertString = "INSERT INTO " + userInfoTable + "(" +
            userIDColumn + "," + usernameColumn + "," + phoneColumn + "," + passwordColumn + "," +
            createdTimeColumn + "," + lastModifiedColumn + ") " +
            "VALUES(?,?,?,?,?,?)"
          log.debug("insertString: " + insertString)
          val currentTime = Instant.now
          val statement = session.prepare(insertString)
          val boundStatement = new BoundStatement(statement).setString(0, userID)
                                                            .setString(1, username)
                                                            .setString(2, phonenum)
                                                            .setString(3, password)
                                                            .setTimestamp(4, java.util.Date.from(currentTime))
                                                            .setTimestamp(5, java.util.Date.from(currentTime))
          val res = await(session.executeAsync(boundStatement).toScalaFuture)
          log.info("insert new user success")
          log.info("generate Json Web Token with " + userID + "...")
          val token = addPrefixToToken(generateToken(userID))

          log.info("token: " + token)
          await(setTokenExpires(token, AuthType.LOCAL, "", userID, username, tokenExpiresIn))
          val signupResponse = new Response.SignupResponse()
                                           .withUserId(userID)
                                           .withUsername(username)
                                           .withToken(token)
                                           .withExpiresIn(tokenExpiresIn)
          response.withResult(ResultCode.SUCCESS)
                  .withErrorDescription("OK")
                  .withSignupResponse(signupResponse)
        }
      }
    }
    // just log
    future onFailure {
      case error: Throwable => 
        log.error("handleSignupRequest async{...} error: " + error)
    }

    future
  }

  /**
   * @return  (isCorrected: Boolean, userID: String, username: String)
   */
  def isPasswordCorrected(
    tableName: String, 
    keyColumn: String, 
    key: String,
    password: String): Future[(Boolean, String, String)] = {
    async{
      log.info("check password by " + tableName)
      val session = client.get.getSession
      val queryString = "SELECT " + userIDColumn + ", " + passwordColumn + ", " +
        usernameColumn + " FROM " + tableName + " WHERE " + keyColumn + "=?"
      log.debug("queryString: " + queryString)
      val statement = session.prepare(queryString)

      val boundStatement = new BoundStatement(statement).setString(0, key)
      val res = await(session.executeAsync(boundStatement).toScalaFuture)
      log.info("query table " + tableName + " success")
      val result = res.all.asScala.toList
      if (result.length >0){
        val row = result.head
        val id: String = row.getString(userIDColumn)
        val expectedPassword: String = row.getString(passwordColumn)
        val username: String = row.getString(usernameColumn)
        log.debug("user(id, password, username): " + "(" + id + "," + expectedPassword + "," + username + ")")
        if (expectedPassword != password){
          log.info("password error: expected password is " + expectedPassword + " not " + password)
          (false, "", "")
        }else{
          (true, id, username)
        }
      }else{
        throw new NoSuchElementException("user " + key + " not existed")
      }
    }
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
    userID: String,
    username: String,
    expiresIn: Int): Future[Unit] = {
    async{
      log.debug("set token expires")
      val expires = Instant.now.plusSeconds(expiresIn)
      log.info("token[" + authToken + "] expires at " + expires.toString)
      val session = client.get.getSession

      val columns = Array(authTokenColumn, 
                          authNameColumn, 
                          authIDColumn, 
                          authUserIDColumn, 
                          authUsernameColumn, 
                          authExpiresColumn)
      val insertString = "INSERT INTO " + authTable + "(" +
        columns.reduce((a,b) => a + "," + b) + ") " +
        "VALUES(?,?,?,?,?,?) USING TTL " + expiresIn
      log.debug("insertString: " + insertString)

      val statement = session.prepare(insertString)
      val boundStatement = new BoundStatement(statement).setString(0, authToken)
                                                        .setString(1, authName.toString)
                                                        .setString(2, authID)
                                                        .setString(3, userID)
                                                        .setString(4, username)
                                                        .setTimestamp(5, java.util.Date.from(expires))
      val res = await(session.executeAsync(boundStatement).toScalaFuture)
      log.info("query table " + authTable + " success")
    }
  }

  def handleLoginRequest(req: Request.LoginRequest): Future[Response] = {
    val future = async{
      val session = client.get.getSession
      var response = new Response()
      // check request
      val password = req.password
      val (loginType, name: String) = {
        if (req.name.isPhonenum)
          (LoginType.LOGIN_BY_PHONENUM, req.getPhonenum)
        else if(req.name.isEmail)
          (LoginType.LOGIN_BY_EMAIL, req.getEmail)
        else
          (LoginType.LOGIN_TYPE_EMPTY, "")
      }
      if (loginType == LoginType.LOGIN_TYPE_EMPTY || name.isEmpty){
        response.withResult(ResultCode.ILLEGAL_ARGUMENT)
                .withErrorDescription("name cannot be empty.")
                .withLoginResponse(new Response.LoginResponse())
      }
      else if(password.isEmpty){
        response.withResult(ResultCode.INVALID_PASSWORD)
                .withErrorDescription("password cannot be empty.")
                .withLoginResponse(new Response.LoginResponse())
      }else{
        val userExisted: Boolean = loginType match {
          case LoginType.LOGIN_BY_PHONENUM => 
            await(isUserExisted(userInfoTable, phoneColumn, name))
          case LoginType.LOGIN_BY_EMAIL => 
            await(isUserExisted(userInfoTable, emailColumn, name))
          case _ => 
            throw new RuntimeException("loginType error, there is a critical server error if this message show up")
        }
        log.info("isUserExisted: " + userExisted)
        if (!userExisted){
          response.withResult(ResultCode.ILLEGAL_ARGUMENT)
                  .withErrorDescription("user " + name + " not existed.")
                  .withLoginResponse(new Response.LoginResponse())
        }else{
          val (isCorrect: Boolean, userID: String, username: String) = loginType match {
            case LoginType.LOGIN_BY_PHONENUM => 
              await(isPasswordCorrected(userInfoTable, phoneColumn, name, password))
            case LoginType.LOGIN_BY_EMAIL => 
              await(isPasswordCorrected(userInfoTable, emailColumn, name, password))
            case _ =>
              throw new RuntimeException("loginType error, there is a critical server error if this message show up")
          }
          log.info("isPasswordCorrected: " + isCorrect)
          if (!isCorrect){
            response.withResult(ResultCode.INVALID_PASSWORD)
                    .withErrorDescription("password incorrected.")
                    .withLoginResponse(new Response.LoginResponse())
          }else{
            log.info("generate Json Web Token with " + userID + "...")
            val token = addPrefixToToken(generateToken(userID), loginType)

            log.info("token: " + token)
            await(setTokenExpires(token, AuthType.LOCAL, "", userID, username, tokenExpiresIn))
            val loginRes = new Response.LoginResponse()
                                       .withUserId(userID)
                                       .withUsername(username)
                                       .withToken(token)
                                       .withExpiresIn(tokenExpiresIn)
            response.withResult(ResultCode.SUCCESS)
                    .withLoginResponse(loginRes)
          }
        }
      }
    }
    // just log
    future onFailure {
      case error: Throwable => 
        log.error("handleLoginRequest async{...} error: " + error)
    }
    future
  }

  /**
   * @return  (isExpired: Boolean, expiresIn: Int, userID: String, username: String)
   */
  def isTokenExpired(token: String): Future[(Boolean, Int, String, String)] = {
    async{
      log.info("checking whether token is expired, token is " + token)
      val session = client.get.getSession

      val queryString = "SELECT " + authUserIDColumn + ", " + authUsernameColumn + 
        ", ttl(" + authUserIDColumn + ")" +
        " FROM " + authTable + " WHERE " + authTokenColumn + "=?"
      log.debug("queryString: " + queryString)

      val statement = session.prepare(queryString)
      val boundStatement = new BoundStatement(statement).setString(0, token)
      val res = await(session.executeAsync(boundStatement).toScalaFuture)
      log.info("query table " + authTable + " success")
      val result = res.all.asScala.toList
      if (result.length >0){
        val row = result.head
        val userID = row.getString(authUserIDColumn)
        val username = row.getString(authUsernameColumn)
        val expiresIn = row.getInt(2)
        (false, expiresIn, userID, username)
      }else{
        (true, 0, "", "")
      }
    }
  }

  def refreshToken(token: String): Future[Unit] = {
    async{

    }
  }

  def handleAuthenticationRequest(req: Request.AuthenticationRequest): Future[Response] = {
    val future = async{
      var response = new Response()
      // check request
      val token: String = req.token
      if (token.isEmpty){
        val authRes = new Response.AuthenticationResponse()
                                  .withIsExpired(true)
                                  .withExpiresIn(0)
        response.withResult(ResultCode.SUCCESS)
                .withAuthenticationResponse(authRes)
      }else{
        val (isExpired: Boolean, expiresIn: Int, userID: String, username: String) = await(isTokenExpired(token))
        val authRes = if(isExpired){
          log.info("token[" + token + "] is expired")
          new Response.AuthenticationResponse()
                      .withIsExpired(isExpired)
                      .withExpiresIn(0)
        }else{
          log.info("user had logon, userID = " + userID + 
            ", expires in " + expiresIn + "seconds.")
          new Response.AuthenticationResponse()
                      .withIsExpired(isExpired)
                      .withExpiresIn(expiresIn)
                      .withUserId(userID)
                      .withUsername(username)
        }
        response.withResult(ResultCode.SUCCESS)
                .withAuthenticationResponse(authRes)
      }
    }
    // just log
    future onFailure {
      case error: Throwable => 
        log.error("handleAuthenticationRequest async{...} error: " + error)
    }
    future
  }

  def clearToken(token: String): Future[Unit] = {
    async{
      log.info("clearToken, token is " + token)
      val session = client.get.getSession

      val queryString = "DELETE FROM " + authTable + " WHERE " + authTokenColumn + "=?"
      log.debug("queryString: " + queryString)

      val statement = session.prepare(queryString)
      val boundStatement = new BoundStatement(statement).setString(0, token)
      val res = await(session.executeAsync(boundStatement).toScalaFuture)
      log.info("query table " + authTable + " success")
    }
  }

  def handleLogoutRequest(req: Request.LogoutRequest): Future[Response] = {
    val future = async{
      var response = new Response()
      // check request
      val token: String = req.token
      if (token.isEmpty){
        response.withResult(ResultCode.SUCCESS)
                .withLogoutResponse(new Response.LogoutResponse())
      }else{
        log.debug("clearing token")
        await(clearToken(token))
        log.debug("clear token success")
        response.withResult(ResultCode.SUCCESS)
                .withLogoutResponse(new Response.LogoutResponse())
      }
    }
    // just log
    future onFailure {
      case error: Throwable => 
        log.error("handleLogoutRequest async{...} error: " + error)
    }
    future
  }

  def handleQueryUserInfoRequest(req: Request.QueryUserInfoRequest): Future[Response] = {
    val future = async{
      var response = new Response()
      // check request
      val token: String = req.token
      if (token.isEmpty) {
        response.withResult(ResultCode.INVALID_SESSION_TOKEN)
                .withErrorDescription("token cannot be empty.")
                .withQueryUserInfoResponse(new Response.QueryUserInfoResponse())
      }else {
        val (isExpired: Boolean, expiresIn: Int, userID: String, username: String) = await(isTokenExpired(token))
        if (isExpired) {
          response.withResult(ResultCode.SESSION_TOKEN_EXPIRED)
                  .withErrorDescription("user is not logged in or token is expired.")
                  .withQueryUserInfoResponse(new Response.QueryUserInfoResponse())
        }else {
          val user = await(getUserInfo(userID))
          var res = new Response.QueryUserInfoResponse()
          if (user.id != null) res = res.withUserId(user.id)
          if (user.username != null) res = res.withUsername(user.username)
          if (user.email != null) res = res.withEmail(user.email)
          if (user.phonenum != null) res = res.withPhonenum(user.phonenum)
          if (user.securityQuestion1 != null) res = res.withSecurityQuestion1(user.securityQuestion1)
          if (user.securityQuestion2 != null) res = res.withSecurityQuestion2(user.securityQuestion2)
          if (user.securityQuestion3 != null) res = res.withSecurityQuestion3(user.securityQuestion3)
          if (user.addresses != null) res = res.withAddressArray(user.addresses)

          response.withResult(ResultCode.SUCCESS).withQueryUserInfoResponse(res)
        }
      }
    }
    // just log
    future onFailure {
      case error: Throwable => 
        log.error("handleQueryUserInfoRequest async{...} error: " + error)
    }
    future
  }

  def handleUpdateUserInfoRequest(req: Request.UpdateUserInfoRequest): Future[Response] = {
    val future = async{
      var response = new Response()
      // check request
      req match {
        case _ if req.token.isEmpty =>
          response.withResult(ResultCode.INVALID_SESSION_TOKEN)
                  .withErrorDescription("token cannot be empty.")
        case _ if req.data.length <= 0 =>
          response.withResult(ResultCode.ILLEGAL_ARGUMENT)
                  .withErrorDescription("update data cannot be empty.")
        case _ =>
          val (isExpired: Boolean, expiresIn: Int, userID: String, username: String) = 
            await(isTokenExpired(req.token))
          if (isExpired) {
            response.withResult(ResultCode.SESSION_TOKEN_EXPIRED)
                    .withErrorDescription("user is not logged in or token is expired.")
          }else {
            val kvs = req.data.map(element => {
              val valueOneOf = element.value
              valueOneOf match {
                case _ if valueOneOf.isUsername =>
                  (usernameColumn, valueOneOf.username.get)
                case _ if valueOneOf.isPassword =>
                  (passwordColumn, valueOneOf.password.get)
                case _ if valueOneOf.isEmail =>
                  (emailColumn, valueOneOf.email.get)
                case _ if valueOneOf.isPhonenum =>
                  (phoneColumn, valueOneOf.phonenum.get)
                case _ if valueOneOf.isSecurityQuestion1 =>
                  // (secQues1Column, valueOneOf.securityQuestion1.get)
                  (null, null)
                case _ if valueOneOf.isSecurityQuestion2 =>
                  // (secQues2Column, valueOneOf.securityQuestion2.get)
                  (null, null)
                case _ if valueOneOf.isSecurityQuestion3 =>
                  // (secQues3Column, valueOneOf.securityQuestion3.get)
                  (null, null)
                case _ =>
                  log.info("update value is empty, skip")
                  (null, null)
              }
            })
            val filteredKeyValues = kvs.filter(ele => ele._1 != null && ele._2 != null)
            if (filteredKeyValues.length > 0) {
              log.debug("updating user info, "+ filteredKeyValues)
              await(updateUserInfo(userID, filteredKeyValues))
            }else {
              log.debug("no new value, skip")
            }
            response.withResult(ResultCode.SUCCESS)
          }
      }
    }
    // just log
    future onFailure {
      case error: Throwable => 
        log.error("handleUpdateUserInfoRequest async{...} error: " + error)
    }
    future
  }

  def updateUserAddress(addressID: String,
                        userID: String,
                        recipientsName: String,
                        recipientsPhone: String,
                        recipientsAddress: String): Future[Unit] = {
    async {
      val session = client.get.getSession
      val updateString = "UPDATE " + addressTable + " SET " + 
                          recipientsNameColumn + " = ?, " +
                          recipientsPhoneColumn + " = ?, " +
                          recipientsAddressColumn + " = ? " +
                          " WHERE " + addressIDColumn + " = ? and " +
                          addressUserIDColumn + " = ?"
      log.debug("updateString: " + updateString)
      val statement = session.prepare(updateString)
      val boundStatement = new BoundStatement(statement).setString(recipientsNameColumn, recipientsName)
                                                        .setString(recipientsPhoneColumn, recipientsPhone)
                                                        .setString(recipientsAddressColumn, recipientsAddress)
                                                        .setString(addressIDColumn, addressID)
                                                        .setString(addressUserIDColumn, userID)
      await(session.executeAsync(boundStatement).toScalaFuture)
      log.debug("updateUserAddress success")
    }
  }

  def deleteUserAddress(addressID: String): Future[Unit] = {
    async {
      val session = client.get.getSession
      val deleteString = "DELETE FROM " + addressTable + 
                          " WHERE " + addressIDColumn + " = ?"
      log.debug("deleteString: " + deleteString)
      val statement = session.prepare(deleteString)
      val boundStatement = new BoundStatement(statement).setString(0, addressID)
      await(session.executeAsync(boundStatement).toScalaFuture)
      log.debug("deleteUserAddress success")
    }
  }

  def handleAddUserAddressRequest(req: Request.AddUserAddressRequest): Future[Response] = {
    val future = async{
      var response = new Response()
      // check request
      req match {
        case _ if req.token.isEmpty =>
          response.withResult(ResultCode.INVALID_SESSION_TOKEN)
                  .withErrorDescription("token cannot be empty.")
        case _ if req.recipientsName.isEmpty =>
          response.withResult(ResultCode.ILLEGAL_ARGUMENT)
                  .withErrorDescription("recipientsName cannot be empty.")
        case _ if req.recipientsPhone.isEmpty =>
          response.withResult(ResultCode.ILLEGAL_ARGUMENT)
                  .withErrorDescription("recipientsPhone cannot be empty.")
        case _ if req.recipientsAddress.isEmpty =>
          response.withResult(ResultCode.ILLEGAL_ARGUMENT)
                  .withErrorDescription("recipientsAddress cannot be empty.")
        case _ =>
          val (isExpired: Boolean, expiresIn: Int, userID: String, username: String) = 
            await(isTokenExpired(req.token))
          if (isExpired) {
            response.withResult(ResultCode.SESSION_TOKEN_EXPIRED)
                    .withErrorDescription("user is not logged in or token is expired.")
          }else {
            val id = UUID.randomUUID.toString
            log.debug("add new user address (" + id + "," + username + "," + req.recipientsName + "," +
                      req.recipientsPhone + "," + req.recipientsAddress + ")")
            await(updateUserAddress(addressID = id,
                                    userID = userID,
                                    recipientsName = req.recipientsName,
                                    recipientsPhone = req.recipientsPhone,
                                    recipientsAddress = req.recipientsAddress))
            response.withResult(ResultCode.SUCCESS)
          }
      }
    }
    // just log
    future onFailure {
      case error: Throwable => 
        log.error("handleAddUserAddressRequest async{...} error: " + error)
    }
    future
  }

  def handleUpdateUserAddressRequest(req: Request.UpdateUserAddressRequest): Future[Response] = {
    val future = async{
      var response = new Response()
      // check request
      req match {
        case _ if req.token.isEmpty =>
          response.withResult(ResultCode.INVALID_SESSION_TOKEN)
                  .withErrorDescription("token cannot be empty.")
        case _ if req.id.isEmpty =>
          response.withResult(ResultCode.ILLEGAL_ARGUMENT)
                  .withErrorDescription("id cannot be empty.")
        case _ if req.recipientsName.isEmpty =>
          response.withResult(ResultCode.ILLEGAL_ARGUMENT)
                  .withErrorDescription("recipientsName cannot be empty.")
        case _ if req.recipientsPhone.isEmpty =>
          response.withResult(ResultCode.ILLEGAL_ARGUMENT)
                  .withErrorDescription("recipientsPhone cannot be empty.")
        case _ if req.recipientsAddress.isEmpty =>
          response.withResult(ResultCode.ILLEGAL_ARGUMENT)
                  .withErrorDescription("recipientsAddress cannot be empty.")
        case _ =>
          val (isExpired: Boolean, expiresIn: Int, userID: String, username: String) = 
            await(isTokenExpired(req.token))
          if (isExpired) {
            response.withResult(ResultCode.SESSION_TOKEN_EXPIRED)
                    .withErrorDescription("user is not logged in or token is expired.")
          }else {
            val id = req.id
            log.debug("update user address (" + id + "," + username + "," + req.recipientsName + "," +
                      req.recipientsPhone + "," + req.recipientsAddress + ")")
            await(updateUserAddress(addressID = id,
                                    userID = userID,
                                    recipientsName = req.recipientsName,
                                    recipientsPhone = req.recipientsPhone,
                                    recipientsAddress = req.recipientsAddress))
            response.withResult(ResultCode.SUCCESS)
          }
      }
    }
    // just log
    future onFailure {
      case error: Throwable => 
        log.error("handleUpdateUserAddressRequest async{...} error: " + error)
    }
    future
  }

  def handleDeleteUserAddressRequest(req: Request.DeleteUserAddressRequest): Future[Response] = {
    val future = async{
      var response = new Response()
      // check request
      req match {
        case _ if req.token.isEmpty =>
          response.withResult(ResultCode.INVALID_SESSION_TOKEN)
                  .withErrorDescription("token cannot be empty.")
        case _ if req.id.isEmpty =>
          response.withResult(ResultCode.ILLEGAL_ARGUMENT)
                  .withErrorDescription("id cannot be empty.")
        case _ =>
          val (isExpired: Boolean, expiresIn: Int, userID: String, username: String) = 
            await(isTokenExpired(req.token))
          if (isExpired) {
            response.withResult(ResultCode.SESSION_TOKEN_EXPIRED)
                    .withErrorDescription("user is not logged in or token is expired.")
          }else {
            val id = req.id
            log.debug("delete user address (" + id + ")")
            await(deleteUserAddress(addressID = id))
            response.withResult(ResultCode.SUCCESS)
          }
      }
    }
    // just log
    future onFailure {
      case error: Throwable => 
        log.error("handleDeleteUserAddressRequest async{...} error: " + error)
    }
    future
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
    case req: Request.LogoutRequest => {
      log.info("receive logout request: " + req.toString)
      handleLogoutRequest(req) pipeTo sender
    }
    case req: Request.QueryUserInfoRequest => {
      log.info("receive get user info request: " + req.toString)
      handleQueryUserInfoRequest(req) pipeTo sender
    }
    case req: Request.UpdateUserInfoRequest => {
      log.info("receive update user info request: " + req.toString)
      handleUpdateUserInfoRequest(req) pipeTo sender
    }
    case req: Request.AddUserAddressRequest => {
      log.info("receive add user address request: " + req.toString)
      handleAddUserAddressRequest(req) pipeTo sender
    }
    case req: Request.UpdateUserAddressRequest => {
      log.info("receive update user address request: " + req.toString)
      handleUpdateUserAddressRequest(req) pipeTo sender
    }
    case req: Request.DeleteUserAddressRequest => {
      log.info("receive delete user address request: " + req.toString)
      handleDeleteUserAddressRequest(req) pipeTo sender
    }
    case default => 
      log.info("receive unknow request: " + default)
  }//receive
}