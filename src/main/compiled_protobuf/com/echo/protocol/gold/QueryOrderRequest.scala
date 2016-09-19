// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.echo.protocol.gold



@SerialVersionUID(0L)
final case class QueryOrderRequest(
    orderId: String = ""
    ) extends com.trueaccord.scalapb.GeneratedMessage with com.trueaccord.scalapb.Message[QueryOrderRequest] with com.trueaccord.lenses.Updatable[QueryOrderRequest] {
    @transient
    private[this] var __serializedSizeCachedValue: Int = 0
    private[this] def __computeSerializedValue(): Int = {
      var __size = 0
      if (orderId != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(1, orderId) }
      __size
    }
    final override def serializedSize: Int = {
      var read = __serializedSizeCachedValue
      if (read == 0) {
        read = __computeSerializedValue()
        __serializedSizeCachedValue = read
      }
      read
    }
    def writeTo(`_output__`: com.google.protobuf.CodedOutputStream): Unit = {
      {
        val __v = orderId
        if (__v != "") {
          _output__.writeString(1, __v)
        }
      };
    }
    def mergeFrom(`_input__`: com.google.protobuf.CodedInputStream): com.echo.protocol.gold.QueryOrderRequest = {
      var __orderId = this.orderId
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __orderId = _input__.readString()
          case tag => _input__.skipField(tag)
        }
      }
      com.echo.protocol.gold.QueryOrderRequest(
          orderId = __orderId
      )
    }
    def withOrderId(__v: String): QueryOrderRequest = copy(orderId = __v)
    def getField(__field: com.google.protobuf.Descriptors.FieldDescriptor): scala.Any = {
      __field.getNumber match {
        case 1 => {
          val __t = orderId
          if (__t != "") __t else null
        }
      }
    }
    override def toString: String = com.trueaccord.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.echo.protocol.gold.QueryOrderRequest
}

object QueryOrderRequest extends com.trueaccord.scalapb.GeneratedMessageCompanion[com.echo.protocol.gold.QueryOrderRequest] {
  implicit def messageCompanion: com.trueaccord.scalapb.GeneratedMessageCompanion[com.echo.protocol.gold.QueryOrderRequest] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[com.google.protobuf.Descriptors.FieldDescriptor, scala.Any]): com.echo.protocol.gold.QueryOrderRequest = {
    require(__fieldsMap.keys.forall(_.getContainingType() == descriptor), "FieldDescriptor does not match message type.")
    val __fields = descriptor.getFields
    com.echo.protocol.gold.QueryOrderRequest(
      __fieldsMap.getOrElse(__fields.get(0), "").asInstanceOf[String]
    )
  }
  def descriptor: com.google.protobuf.Descriptors.Descriptor = GoldProto.descriptor.getMessageTypes.get(3)
  def messageCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__field)
  def enumCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__field)
  lazy val defaultInstance = com.echo.protocol.gold.QueryOrderRequest(
  )
  implicit class QueryOrderRequestLens[UpperPB](_l: com.trueaccord.lenses.Lens[UpperPB, com.echo.protocol.gold.QueryOrderRequest]) extends com.trueaccord.lenses.ObjectLens[UpperPB, com.echo.protocol.gold.QueryOrderRequest](_l) {
    def orderId: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.orderId)((c_, f_) => c_.copy(orderId = f_))
  }
  final val ORDER_ID_FIELD_NUMBER = 1
}
