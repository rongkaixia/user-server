package com.echo.protocol

import com.trueaccord.scalapb.json.JsonFormat

object Serializer{
  def toJsonString(msg: Message): String = {
    JsonFormat.toJsonString(msg)
  }

  def fromJsonString(msg: String): Message = {
    JsonFormat.fromJsonString[Message](msg)
  }
  // def serialize(msg: Message) = toJsonString(msg)
  def serialize(msg: Message) = msg.toByteArray

  def deserialize(msg: Array[Byte]) = Message.parseFrom(msg)

  def deserialize(msg: String) = fromJsonString(msg)
}