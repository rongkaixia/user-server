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
      var token = client.signup()
      client.logout(token)
      token = client.login()
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

}