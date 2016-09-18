// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.echo.protocol.common



@SerialVersionUID(0L)
final case class ResponseHeader(
    result: com.echo.protocol.common.ResultCode = com.echo.protocol.common.ResultCode.RESULT_CODE_EMPTY,
    resultDescription: String = ""
    ) extends com.trueaccord.scalapb.GeneratedMessage with com.trueaccord.scalapb.Message[ResponseHeader] with com.trueaccord.lenses.Updatable[ResponseHeader] {
    @transient
    lazy val serializedSize: Int = {
      var __size = 0
      if (result != com.echo.protocol.common.ResultCode.RESULT_CODE_EMPTY) { __size += com.google.protobuf.CodedOutputStream.computeEnumSize(1, result.value) }
      if (resultDescription != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(2, resultDescription) }
      __size
    }
    def writeTo(output: com.google.protobuf.CodedOutputStream): Unit = {
      {
        val __v = result
        if (__v != com.echo.protocol.common.ResultCode.RESULT_CODE_EMPTY) {
          output.writeEnum(1, __v.value)
        }
      };
      {
        val __v = resultDescription
        if (__v != "") {
          output.writeString(2, __v)
        }
      };
    }
    def mergeFrom(__input: com.google.protobuf.CodedInputStream): com.echo.protocol.common.ResponseHeader = {
      var __result = this.result
      var __resultDescription = this.resultDescription
      var _done__ = false
      while (!_done__) {
        val _tag__ = __input.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 8 =>
            __result = com.echo.protocol.common.ResultCode.fromValue(__input.readEnum())
          case 18 =>
            __resultDescription = __input.readString()
          case tag => __input.skipField(tag)
        }
      }
      com.echo.protocol.common.ResponseHeader(
          result = __result,
          resultDescription = __resultDescription
      )
    }
    def withResult(__v: com.echo.protocol.common.ResultCode): ResponseHeader = copy(result = __v)
    def withResultDescription(__v: String): ResponseHeader = copy(resultDescription = __v)
    def getField(__field: com.google.protobuf.Descriptors.FieldDescriptor): scala.Any = {
      __field.getNumber match {
        case 1 => {
          val __t = result.valueDescriptor
          if (__t.getNumber() != 0) __t else null
        }
        case 2 => {
          val __t = resultDescription
          if (__t != "") __t else null
        }
      }
    }
    override def toString: String = com.trueaccord.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.echo.protocol.common.ResponseHeader
}

object ResponseHeader extends com.trueaccord.scalapb.GeneratedMessageCompanion[ResponseHeader] {
  implicit def messageCompanion: com.trueaccord.scalapb.GeneratedMessageCompanion[ResponseHeader] = this
  def fromFieldsMap(__fieldsMap: Map[com.google.protobuf.Descriptors.FieldDescriptor, scala.Any]): com.echo.protocol.common.ResponseHeader = {
    require(__fieldsMap.keys.forall(_.getContainingType() == descriptor), "FieldDescriptor does not match message type.")
    val __fields = descriptor.getFields
    com.echo.protocol.common.ResponseHeader(
      com.echo.protocol.common.ResultCode.fromValue(__fieldsMap.getOrElse(__fields.get(0), com.echo.protocol.common.ResultCode.RESULT_CODE_EMPTY.valueDescriptor).asInstanceOf[com.google.protobuf.Descriptors.EnumValueDescriptor].getNumber),
      __fieldsMap.getOrElse(__fields.get(1), "").asInstanceOf[String]
    )
  }
  def descriptor: com.google.protobuf.Descriptors.Descriptor = CommonProto.descriptor.getMessageTypes.get(0)
  def messageCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__field)
  def enumCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedEnumCompanion[_] = {
    require(__field.getContainingType() == descriptor, "FieldDescriptor does not match message type.")
    __field.getNumber match {
      case 1 => com.echo.protocol.common.ResultCode
    }
  }
  lazy val defaultInstance = com.echo.protocol.common.ResponseHeader(
  )
  implicit class ResponseHeaderLens[UpperPB](_l: com.trueaccord.lenses.Lens[UpperPB, ResponseHeader]) extends com.trueaccord.lenses.ObjectLens[UpperPB, ResponseHeader](_l) {
    def result: com.trueaccord.lenses.Lens[UpperPB, com.echo.protocol.common.ResultCode] = field(_.result)((c_, f_) => c_.copy(result = f_))
    def resultDescription: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.resultDescription)((c_, f_) => c_.copy(resultDescription = f_))
  }
  final val RESULT_FIELD_NUMBER = 1
  final val RESULT_DESCRIPTION_FIELD_NUMBER = 2
}
