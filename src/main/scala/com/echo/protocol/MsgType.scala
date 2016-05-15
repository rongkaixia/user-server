// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.echo.protocol



sealed trait MsgType extends com.trueaccord.scalapb.GeneratedEnum {
  type EnumType = MsgType
  def isSignupRequest: Boolean = false
  def isLoginRequest: Boolean = false
  def isAuthenticationRequest: Boolean = false
  def isSignupResponse: Boolean = false
  def isLoginResponse: Boolean = false
  def isAuthenticationResponse: Boolean = false
  def isUnrecognized: Boolean = false
  def companion: com.trueaccord.scalapb.GeneratedEnumCompanion[MsgType] = MsgType
}

object MsgType extends com.trueaccord.scalapb.GeneratedEnumCompanion[MsgType] {
  implicit def enumCompanion: com.trueaccord.scalapb.GeneratedEnumCompanion[MsgType] = this
  @SerialVersionUID(0L)
  case object SIGNUP_REQUEST extends MsgType {
    val value = 0
    val index = 0
    val name = "SIGNUP_REQUEST"
    override def isSignupRequest: Boolean = true
  }
  
  @SerialVersionUID(0L)
  case object LOGIN_REQUEST extends MsgType {
    val value = 1
    val index = 1
    val name = "LOGIN_REQUEST"
    override def isLoginRequest: Boolean = true
  }
  
  @SerialVersionUID(0L)
  case object AUTHENTICATION_REQUEST extends MsgType {
    val value = 2
    val index = 2
    val name = "AUTHENTICATION_REQUEST"
    override def isAuthenticationRequest: Boolean = true
  }
  
  @SerialVersionUID(0L)
  case object SIGNUP_RESPONSE extends MsgType {
    val value = 65536
    val index = 3
    val name = "SIGNUP_RESPONSE"
    override def isSignupResponse: Boolean = true
  }
  
  @SerialVersionUID(0L)
  case object LOGIN_RESPONSE extends MsgType {
    val value = 65537
    val index = 4
    val name = "LOGIN_RESPONSE"
    override def isLoginResponse: Boolean = true
  }
  
  @SerialVersionUID(0L)
  case object AUTHENTICATION_RESPONSE extends MsgType {
    val value = 65538
    val index = 5
    val name = "AUTHENTICATION_RESPONSE"
    override def isAuthenticationResponse: Boolean = true
  }
  
  @SerialVersionUID(0L)
  case class Unrecognized(value: Int) extends MsgType {
    val name = "UNRECOGNIZED"
    val index = -1
    override def isUnrecognized: Boolean = true
  }
  
  lazy val values = Seq(SIGNUP_REQUEST, LOGIN_REQUEST, AUTHENTICATION_REQUEST, SIGNUP_RESPONSE, LOGIN_RESPONSE, AUTHENTICATION_RESPONSE)
  def fromValue(value: Int): MsgType = value match {
    case 0 => SIGNUP_REQUEST
    case 1 => LOGIN_REQUEST
    case 2 => AUTHENTICATION_REQUEST
    case 65536 => SIGNUP_RESPONSE
    case 65537 => LOGIN_RESPONSE
    case 65538 => AUTHENTICATION_RESPONSE
    case __other => Unrecognized(__other)
  }
  def descriptor: com.google.protobuf.Descriptors.EnumDescriptor = ProtocolComEchoProtocolProto.descriptor.getEnumTypes.get(0)
}