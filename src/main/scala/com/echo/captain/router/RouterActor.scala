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
    if (res.content.isLoginResponse)
      new Message().withMsgType(MsgType.LOGIN_RESPONSE).withResponse(res)
    else if(res.content.isSignupResponse)
      new Message().withMsgType(MsgType.SIGNUP_RESPONSE).withResponse(res)
    else if(res.content.isAuthenticationResponse)
      new Message().withMsgType(MsgType.AUTHENTICATION_RESPONSE).withResponse(res)
    else
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

  def extractRequest[T](msg: Message)(implicit m: Manifest[T]): Directive1[T] = {
    log.info("extractRequest: " + msg)
    if (!msg.body.isRequest){
      log.info("invalid message, message MUST BE a request")
      reject(new InvalidMessageRejection("invalid message, message MUST BE a request"))
    }else{
      val request = msg.getRequest
      manifest[T] match {
        case c if c == manifest[Request.LoginRequest] => {
          log.debug("request.content.isLoginRequest: " + request.content.isLoginRequest)
          if (!request.content.isLoginRequest){
            reject(new InvalidMessageRejection("invalid message, message MUST BE a login request"))
          }else{
            provide(request.getLoginRequest.asInstanceOf[T])
          }
        }
        case c if c == manifest[Request.SignupRequest] => {
          log.debug("request.content.isSignupRequest: " + request.content.isSignupRequest)
          if (!request.content.isSignupRequest){
            reject(new InvalidMessageRejection("invalid message, message MUST BE a signup request"))
          }else{
            provide(request.getSignupRequest.asInstanceOf[T])
          }
        }
        case c if c == manifest[Request.AuthenticationRequest] => {
          log.debug("request.content.isAuthenticationRequest: " + request.content.isAuthenticationRequest)
          if (!request.content.isAuthenticationRequest){
            reject(new InvalidMessageRejection("invalid message, message MUST BE a authentication request"))
          }else{
            provide(request.getAuthenticationRequest.asInstanceOf[T])
          }
        }
        case _ => {
          log.error("unknow extractRequest type")
          reject()
        }
      }
    }
  }

  def handleRequest[T](req: T) = {
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
    path("login") {
      post { //这里记得千万不能有 `ctx =>`，不然inner route的所有reject都会失效，原因现在也不清楚
        deserialize { msg =>
          extractRequest[Request.LoginRequest](msg).apply { req =>
            handleRequest(req)
          }
        }
      }//post
    }~
    path("signup") {
      post {
        deserialize { msg =>
          extractRequest[Request.SignupRequest](msg).apply { req =>
            handleRequest(req)
          }
        }
      }//post
    }~
    path("auth") {
      post {
        deserialize { msg =>
          extractRequest[Request.AuthenticationRequest](msg).apply { req =>
            handleRequest(req)
          }
        }
      }//post
    }
  }//route

  def receive = runRoute(router)
}
