package com.echo.captain

import scala.async.Async.{async, await}
import scala.concurrent.duration._
import scala.concurrent.Future
import scala.util.{Success, Failure}
import scala.reflect._
import scala.reflect.runtime.universe._

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import akka.event.Logging
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy._

import spray.json._
import spray.routing._
import spray.http._
import Directives._

import com.echo.common._
import com.echo.protocol._
import com.trueaccord.scalapb.json.JsonFormat

class RouterActor() extends HttpServiceActor with akka.actor.ActorLogging {
  import context.dispatcher // ExecutionContext for the futures and scheduler
  val captainService = context.actorOf(Props[CaptainService], "CaptainService") 
  val actorTimeout = context.system.settings.config.getInt("echo.captain.actor_timeout")

  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
      case _: Exception => {log.info("Exception"); Escalate}
    }

  def packResponse(res: Response): Message = {
      new Message().withResponse(res)
  }

  // implicit RejectionHandler
  implicit val CaptainRejectionHandler = RejectionHandler {
    case MethodRejection(supported: HttpMethod) :: _ =>
      val response = new Response()
        .withResult(ResultCode.METHOD_NOT_ALLOW)
        .withErrorDescription("method not allowed, supported methods: " + supported)
      complete(Serializer.serialize(packResponse(response)))
    case MsgTypeRejection(expected: MsgType) :: _ =>
      val response = new Response()
        .withResult(ResultCode.MESSAGE_TYPE_ERROR)
        .withErrorDescription("msgType error, msgType MUST BE" + expected)
      complete(Serializer.serialize(packResponse(response)))
    case InvalidMessageRejection(info: String) :: _ =>
      val response = new Response()
        .withResult(ResultCode.INVALID_MESSAGE)
        .withErrorDescription(info)
      complete(Serializer.serialize(packResponse(response)))
    case _ =>
      val response = new Response()
        .withResult(ResultCode.REQUEST_RESOURCE_NOT_FOUND)
        .withErrorDescription("The requested resource could not be found.")
      complete(Serializer.serialize(packResponse(response)))
  }

  def deserialize: Directive1[Message] = 
    extract((ctx: RequestContext) => ctx.request.entity.data.toByteArray).flatMap{ 
      content => {
        try{
          log.info("deserialize: " + content.map(_.toString).reduce((a,b)=>a + "," + b))
          provide(Serializer.deserialize(content))
        }catch{
          case e: Exception => 
            log.info("deserialize error, invalid message")
            reject(new InvalidMessageRejection("invalid message, cannot deserialize message"))
        }
      }
    }

  def handleRequest(msg: Message) = {
    log.info("extractRequest: " + msg)
    if (!msg.body.isRequest){
      log.info("invalid message, message MUST BE a request")
      reject(new InvalidMessageRejection("invalid message, message MUST BE a request"))
    }else {
      val request = msg.getRequest
      if (request.content.isSignupRequest) sendRequest(request.getSignupRequest)
      else if (request.content.isLoginRequest) sendRequest(request.getLoginRequest)
      else if (request.content.isLogoutRequest) sendRequest(request.getLogoutRequest)
      else if (request.content.isAuthenticationRequest) sendRequest(request.getAuthenticationRequest)
      else if (request.content.isQueryUserInfoRequest) sendRequest(request.getQueryUserInfoRequest)
      else if (request.content.isUpdateUserInfoRequest) sendRequest(request.getUpdateUserInfoRequest)
      else if (request.content.isAddUserAddressRequest) sendRequest(request.getAddUserAddressRequest)
      else if (request.content.isUpdateUserAddressRequest) sendRequest(request.getUpdateUserAddressRequest)
      else if (request.content.isDeleteUserAddressRequest) sendRequest(request.getDeleteUserAddressRequest)
      else {
        log.info("invalid message, request.content is not defined")
        reject(new InvalidMessageRejection("invalid message, request.content is not defined"))
      }
    }
  }

  def sendRequest[T](req: T) = {
    log.info("handleRequest: " + req)
    val f: Future[Response] = async{
      implicit val timeout = Timeout(actorTimeout milliseconds)
      await(captainService ? req).asInstanceOf[Response]
    }
    onComplete(f) {
      case Success(response: Response) => 
        log.info("send response: " + response.toString)
        val encodedMsg = Serializer.serialize(packResponse(response))
        log.debug("binary response: " + encodedMsg.map(_.toInt.toString).reduce((a,b)=>a + "," + b))
        complete(encodedMsg)
      case Failure(ex) => 
        log.error("handleRequest CaptainService error: " + ex.toString)
        val response = new Response()
          .withResult(ResultCode.INTERNAL_SERVER_ERROR)
          .withErrorDescription(ErrorMessage.InteralServerError)
        log.info("send response: " + response.toString)
        val encodedMsg = Serializer.serialize(packResponse(response))
        log.debug("binary response: " + encodedMsg.map(_.toInt.toString).reduce((a,b)=>a + "," + b))
        complete(encodedMsg)
    }
  }

  val router: Route = {
    path("api" / "v1.0") {
      post {
        deserialize { msg => 
          handleRequest(msg)
        }
      }
    }
  }//route

  def receive = runRoute(router)
}
