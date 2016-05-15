package com.echo.captain

import java.time.Instant

import akka.actor._
import akka.io.IO
import spray.can.Http

/**
 * @author ${user.name}
 */
object App {
  
  def main(args : Array[String]) {
    implicit val system = ActorSystem()
    val cfg = system.settings.config

    // val reqManager = system.actorOf(Props[CaptainService], "CaptainService") 
    // val coreAPI = system.actorOf(CoreAPI.props(reqManager), "CoreAPI")

    val RouterActor = system.actorOf(Props[RouterActor], "CoreAPI")
    IO(Http) ! Http.Bind(RouterActor, interface="0.0.0.0", port=cfg.getInt("echo.captain.service_port"))
  }
}