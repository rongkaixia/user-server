package com.echo.captain

import spray.json._
import spray.routing._
import spray.http.HttpData
import spray.http.HttpMethods._

import com.echo.protocol._

case class MsgTypeRejection(expected: MsgType) extends Rejection

case class InvalidMessageRejection(info: String) extends Rejection
