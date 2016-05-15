package com.echo.captain

import scala.async.Async.{async, await}
import scala.concurrent.duration._
import scala.concurrent.{ Future, Promise }
import scala.concurrent._

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import akka.event.Logging

import spray.routing._
import spray.http.HttpData
import spray.json._

import com.echo.protocol.{Request, Response}
import com.trueaccord.scalapb.json.JsonFormat

class CaptainService() extends Actor with akka.actor.ActorLogging{
  import context.dispatcher // ExecutionContext for the futures and scheduler

  val cfg = context.system.settings.config
  
  def receive = {
    case m: Request.LoginRequest => {

      log.info("receive request: " + m.toString)

      val res = new Response()
      .withResult(Response.ResultCode.SUCCESS)
      .withErrorDescription("")
      .withLoginResponse(new Response.LoginResponse().withToken("NSJUENQV24J1KJND1241LJ"))
      sender ! res
      // val f: Future[String] = Future {
      //   Thread.sleep(2000)
      //   "future value"
      // }

      // val f2 = f map { s => 
      //     println("OK!")
      //     println("OK!")

      // }

      // Await.ready(f2, 60 seconds)
      // log.info("response: " + JsonFormat.toJsonString(res))
      // sender ! res
    }
    case _ => {
      log.info("unknow request")
      sender ! akka.actor.Status.Failure(new RuntimeException("unknow request"))
    }
  }
}