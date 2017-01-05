package com.echo.captain

import java.util.concurrent.TimeUnit
import java.util.logging.{Level, Logger}

import io.grpc.{StatusRuntimeException, ManagedChannelBuilder, ManagedChannel}
import com.echo.protocol.captain._

/**
 * [[https://github.com/grpc/grpc-java/blob/v0.13.2/examples/src/main/java/io/grpc/examples/helloworld/HelloWorldClient.java]]
 */
object HelloWorldClient {
  def apply(host: String, port: Int): HelloWorldClient = {
    val channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build
    val blockingStub = CaptainServiceGrpc.blockingStub(channel)
    new HelloWorldClient(channel, blockingStub)
  }

  def main(args: Array[String]): Unit = {
    val client = HelloWorldClient("localhost", 19876)
    try {
      // signup
      var token = client.signup()
      client.logout(token)
      token = client.login()
      client.auth(token)
      var address = new UserAddress(recipientsName="rk", recipientsPhone="15002029322", recipientsAddress="qiaonan")
      client.addAddress(token, address)
      val userInfo = client.queryUserInfo(token)
      address = new UserAddress(addressId = userInfo.addresses.head.addressId,
                                recipientsName="rk", 
                                recipientsPhone="13539274099")
      client.updateAddress(token, address)
      client.queryUserInfo(token)
      // update user info
      client.updateUserInfo(token,
                            username = "rongkai",
                            phonenum = "13539274099",
                            email = "403033080@qq.com",
                            securityQuestion1 = SecurityQuestionPair("question1", "answer1"),
                            securityQuestion2 = SecurityQuestionPair("question2", "answer2"),
                            securityQuestion3 = SecurityQuestionPair("question3", "answer3"))
      client.queryUserInfo(token)

      // update user item
      var cartItem = new CartItem(skuId = "test_product", num = 1)
      client.updateUserCart(token, cartItem)
      client.queryUserInfo(token)
      cartItem = new CartItem(skuId = "test_product", num = 2)
      client.updateUserCart(token, cartItem)
      client.queryUserInfo(token)
      client.deleteUserCart(token, cartItem)
      client.queryUserInfo(token)
      client.logout(token)
    } finally {
      client.shutdown()
    }
  }
}

class HelloWorldClient private(
  private val channel: ManagedChannel,
  private val blockingStub: CaptainServiceGrpc.CaptainServiceBlockingStub
) {
  private[this] val logger = Logger.getLogger(classOf[HelloWorldClient].getName)

  def shutdown(): Unit = {
    channel.shutdown.awaitTermination(5, TimeUnit.SECONDS)
  }

  def signup(): String = {
    logger.info("Will try to send signup request...")
    val request = SignupRequest().withPhonenum("15002029322").withPassword("15002029322")
    try {
      val response = blockingStub.signup(request)
      logger.info("SignResponse: " + response)
      response.token
    }
    catch {
      case e: StatusRuntimeException =>
        logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus)
        null
    }
  }  

  def login(): String = {
    logger.info("Will try to send login request...")
    val request = LoginRequest().withPhonenum("15002029322").withPassword("15002029322")
    try {
      val response = blockingStub.login(request)
      logger.info("LoginResponse: " + response)
      response.token
    }
    catch {
      case e: StatusRuntimeException =>
        logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus)
        null
    }
  }

  def logout(token: String): Unit = {
    logger.info("Will try to send logout request...")
    val request = LogoutRequest().withToken(token)
    try {
      val response = blockingStub.logout(request)
      logger.info("LogoutResponse: " + response)
    }
    catch {
      case e: StatusRuntimeException =>
        logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus)
    }
  }  

  def auth(token: String): Unit = {
    logger.info("Will try to send auth request...")
    val request = AuthRequest().withToken(token)
    try {
      val response = blockingStub.auth(request)
      logger.info("AuthResponse: " + response)
    }
    catch {
      case e: StatusRuntimeException =>
        logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus)
    }
  }

  def addAddress(token: String, userAddress: UserAddress): Unit = {
    logger.info("Will try to send addAddress request...")
    val request = AddUserAddressRequest().withToken(token)
                                         .withRecipientsName(userAddress.recipientsName)
                                         .withRecipientsPhone(userAddress.recipientsPhone)
                                         .withRecipientsAddress(userAddress.recipientsAddress)
                                         .withRecipientsPostcode(userAddress.recipientsPostcode)
    try {
      val response = blockingStub.addUserAddress(request)
      logger.info("AddUserAddressResponse: " + response)
    }
    catch {
      case e: StatusRuntimeException =>
        logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus)
    }
  }

  def updateAddress(token: String, userAddress: UserAddress): Unit = {
    logger.info("Will try to send updateAddress request...")
    val request = UpdateUserAddressRequest().withToken(token)
                                         .withAddressId(userAddress.addressId)
                                         .withRecipientsName(userAddress.recipientsName)
                                         .withRecipientsPhone(userAddress.recipientsPhone)
                                         .withRecipientsAddress(userAddress.recipientsAddress)
                                         .withRecipientsPostcode(userAddress.recipientsPostcode)
    try {
      val response = blockingStub.updateUserAddress(request)
      logger.info("UpdateUserAddressResponse: " + response)
    }
    catch {
      case e: StatusRuntimeException =>
        logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus)
    }
  }

  def queryUserInfo(token: String): UserInfo = {
    logger.info("Will try to send queryUserInfo request...")
    val request = QueryUserInfoRequest().withToken(token)
    try {
      val response = blockingStub.queryUserInfo(request)
      logger.info("QueryUserInfoResponse: " + response)
      response.userInfo.getOrElse(null)
    }
    catch {
      case e: StatusRuntimeException =>
        logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus)
        null
    }
  }

  def updateUserInfo(token: String,
                     username: String = null,
                     phonenum: String = null,
                     email: String = null,
                     securityQuestion1: SecurityQuestionPair = null,
                     securityQuestion2: SecurityQuestionPair = null,
                     securityQuestion3: SecurityQuestionPair = null): Unit = {
    logger.info("Will try to send updateUserInfo request...")
    var request = UpdateUserInfoRequest().withToken(token)
    if (username != null) request = request.withUsername(username)
    if (phonenum != null) request = request.withPhonenum(phonenum)
    if (email != null) request = request.withEmail(email)
    if (securityQuestion1 != null) request = request.withSecurityQuestion1(securityQuestion1)
    if (securityQuestion2 != null) request = request.withSecurityQuestion2(securityQuestion2)
    if (securityQuestion3 != null) request = request.withSecurityQuestion3(securityQuestion3)
    try {
      val response = blockingStub.updateUserInfo(request)
      logger.info("UpdateUserInfoResponse: " + response)
    }
    catch {
      case e: StatusRuntimeException =>
        logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus)
    }
  }

  def updateUserCart(token: String, item: CartItem): Unit = {
    logger.info("Will try to send updateUserCart request...")
    val request = UpdateUserCartRequest().withToken(token)
                                         .withSkuId(item.skuId)
                                         .withNum(item.num)
    try {
      val response = blockingStub.updateUserCart(request)
      logger.info("UpdateUserCartResponse: " + response)
    }
    catch {
      case e: StatusRuntimeException =>
        logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus)
    }
  }

  def deleteUserCart(token: String, item: CartItem): Unit = {
    logger.info("Will try to send deleteUserCart request...")
    val request = DeleteUserCartRequest().withToken(token)
                                         .withSkuId(item.skuId)
    try {
      val response = blockingStub.deleteUserCart(request)
      logger.info("DeleteUserCartRequest: " + response)
    }
    catch {
      case e: StatusRuntimeException =>
        logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus)
    }
  }

}