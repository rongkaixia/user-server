// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.echo.protocol.captain



/** 增加或更新用户的购物车
  */
@SerialVersionUID(0L)
final case class UpdateUserCartRequest(
    token: String = "",
    skuId: String = "",
    num: Int = 0
    ) extends com.trueaccord.scalapb.GeneratedMessage with com.trueaccord.scalapb.Message[UpdateUserCartRequest] with com.trueaccord.lenses.Updatable[UpdateUserCartRequest] {
    @transient
    private[this] var __serializedSizeCachedValue: Int = 0
    private[this] def __computeSerializedValue(): Int = {
      var __size = 0
      if (token != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(1, token) }
      if (skuId != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(2, skuId) }
      if (num != 0) { __size += com.google.protobuf.CodedOutputStream.computeInt32Size(3, num) }
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
        val __v = token
        if (__v != "") {
          _output__.writeString(1, __v)
        }
      };
      {
        val __v = skuId
        if (__v != "") {
          _output__.writeString(2, __v)
        }
      };
      {
        val __v = num
        if (__v != 0) {
          _output__.writeInt32(3, __v)
        }
      };
    }
    def mergeFrom(`_input__`: com.google.protobuf.CodedInputStream): com.echo.protocol.captain.UpdateUserCartRequest = {
      var __token = this.token
      var __skuId = this.skuId
      var __num = this.num
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __token = _input__.readString()
          case 18 =>
            __skuId = _input__.readString()
          case 24 =>
            __num = _input__.readInt32()
          case tag => _input__.skipField(tag)
        }
      }
      com.echo.protocol.captain.UpdateUserCartRequest(
          token = __token,
          skuId = __skuId,
          num = __num
      )
    }
    def withToken(__v: String): UpdateUserCartRequest = copy(token = __v)
    def withSkuId(__v: String): UpdateUserCartRequest = copy(skuId = __v)
    def withNum(__v: Int): UpdateUserCartRequest = copy(num = __v)
    def getField(__field: com.google.protobuf.Descriptors.FieldDescriptor): scala.Any = {
      __field.getNumber match {
        case 1 => {
          val __t = token
          if (__t != "") __t else null
        }
        case 2 => {
          val __t = skuId
          if (__t != "") __t else null
        }
        case 3 => {
          val __t = num
          if (__t != 0) __t else null
        }
      }
    }
    override def toString: String = com.trueaccord.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.echo.protocol.captain.UpdateUserCartRequest
}

object UpdateUserCartRequest extends com.trueaccord.scalapb.GeneratedMessageCompanion[com.echo.protocol.captain.UpdateUserCartRequest] {
  implicit def messageCompanion: com.trueaccord.scalapb.GeneratedMessageCompanion[com.echo.protocol.captain.UpdateUserCartRequest] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[com.google.protobuf.Descriptors.FieldDescriptor, scala.Any]): com.echo.protocol.captain.UpdateUserCartRequest = {
    require(__fieldsMap.keys.forall(_.getContainingType() == descriptor), "FieldDescriptor does not match message type.")
    val __fields = descriptor.getFields
    com.echo.protocol.captain.UpdateUserCartRequest(
      __fieldsMap.getOrElse(__fields.get(0), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(1), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(2), 0).asInstanceOf[Int]
    )
  }
  def descriptor: com.google.protobuf.Descriptors.Descriptor = CaptainProto.descriptor.getMessageTypes.get(13)
  def messageCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__field)
  def enumCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__field)
  lazy val defaultInstance = com.echo.protocol.captain.UpdateUserCartRequest(
  )
  implicit class UpdateUserCartRequestLens[UpperPB](_l: com.trueaccord.lenses.Lens[UpperPB, com.echo.protocol.captain.UpdateUserCartRequest]) extends com.trueaccord.lenses.ObjectLens[UpperPB, com.echo.protocol.captain.UpdateUserCartRequest](_l) {
    def token: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.token)((c_, f_) => c_.copy(token = f_))
    def skuId: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.skuId)((c_, f_) => c_.copy(skuId = f_))
    def num: com.trueaccord.lenses.Lens[UpperPB, Int] = field(_.num)((c_, f_) => c_.copy(num = f_))
  }
  final val TOKEN_FIELD_NUMBER = 1
  final val SKU_ID_FIELD_NUMBER = 2
  final val NUM_FIELD_NUMBER = 3
}
