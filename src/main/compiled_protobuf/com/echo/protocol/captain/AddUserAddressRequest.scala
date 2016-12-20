// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.echo.protocol.captain



@SerialVersionUID(0L)
final case class AddUserAddressRequest(
    token: String = "",
    recipientsName: String = "",
    recipientsPhone: String = "",
    recipientsAddress: String = "",
    recipientsPostcode: String = ""
    ) extends com.trueaccord.scalapb.GeneratedMessage with com.trueaccord.scalapb.Message[AddUserAddressRequest] with com.trueaccord.lenses.Updatable[AddUserAddressRequest] {
    @transient
    private[this] var __serializedSizeCachedValue: Int = 0
    private[this] def __computeSerializedValue(): Int = {
      var __size = 0
      if (token != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(1, token) }
      if (recipientsName != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(3, recipientsName) }
      if (recipientsPhone != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(4, recipientsPhone) }
      if (recipientsAddress != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(5, recipientsAddress) }
      if (recipientsPostcode != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(6, recipientsPostcode) }
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
        val __v = recipientsName
        if (__v != "") {
          _output__.writeString(3, __v)
        }
      };
      {
        val __v = recipientsPhone
        if (__v != "") {
          _output__.writeString(4, __v)
        }
      };
      {
        val __v = recipientsAddress
        if (__v != "") {
          _output__.writeString(5, __v)
        }
      };
      {
        val __v = recipientsPostcode
        if (__v != "") {
          _output__.writeString(6, __v)
        }
      };
    }
    def mergeFrom(`_input__`: com.google.protobuf.CodedInputStream): com.echo.protocol.captain.AddUserAddressRequest = {
      var __token = this.token
      var __recipientsName = this.recipientsName
      var __recipientsPhone = this.recipientsPhone
      var __recipientsAddress = this.recipientsAddress
      var __recipientsPostcode = this.recipientsPostcode
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __token = _input__.readString()
          case 26 =>
            __recipientsName = _input__.readString()
          case 34 =>
            __recipientsPhone = _input__.readString()
          case 42 =>
            __recipientsAddress = _input__.readString()
          case 50 =>
            __recipientsPostcode = _input__.readString()
          case tag => _input__.skipField(tag)
        }
      }
      com.echo.protocol.captain.AddUserAddressRequest(
          token = __token,
          recipientsName = __recipientsName,
          recipientsPhone = __recipientsPhone,
          recipientsAddress = __recipientsAddress,
          recipientsPostcode = __recipientsPostcode
      )
    }
    def withToken(__v: String): AddUserAddressRequest = copy(token = __v)
    def withRecipientsName(__v: String): AddUserAddressRequest = copy(recipientsName = __v)
    def withRecipientsPhone(__v: String): AddUserAddressRequest = copy(recipientsPhone = __v)
    def withRecipientsAddress(__v: String): AddUserAddressRequest = copy(recipientsAddress = __v)
    def withRecipientsPostcode(__v: String): AddUserAddressRequest = copy(recipientsPostcode = __v)
    def getField(__field: com.google.protobuf.Descriptors.FieldDescriptor): scala.Any = {
      __field.getNumber match {
        case 1 => {
          val __t = token
          if (__t != "") __t else null
        }
        case 3 => {
          val __t = recipientsName
          if (__t != "") __t else null
        }
        case 4 => {
          val __t = recipientsPhone
          if (__t != "") __t else null
        }
        case 5 => {
          val __t = recipientsAddress
          if (__t != "") __t else null
        }
        case 6 => {
          val __t = recipientsPostcode
          if (__t != "") __t else null
        }
      }
    }
    override def toString: String = com.trueaccord.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.echo.protocol.captain.AddUserAddressRequest
}

object AddUserAddressRequest extends com.trueaccord.scalapb.GeneratedMessageCompanion[com.echo.protocol.captain.AddUserAddressRequest] {
  implicit def messageCompanion: com.trueaccord.scalapb.GeneratedMessageCompanion[com.echo.protocol.captain.AddUserAddressRequest] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[com.google.protobuf.Descriptors.FieldDescriptor, scala.Any]): com.echo.protocol.captain.AddUserAddressRequest = {
    require(__fieldsMap.keys.forall(_.getContainingType() == descriptor), "FieldDescriptor does not match message type.")
    val __fields = descriptor.getFields
    com.echo.protocol.captain.AddUserAddressRequest(
      __fieldsMap.getOrElse(__fields.get(0), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(1), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(2), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(3), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(4), "").asInstanceOf[String]
    )
  }
  def descriptor: com.google.protobuf.Descriptors.Descriptor = CaptainProto.descriptor.getMessageTypes.get(10)
  def messageCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__field)
  def enumCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__field)
  lazy val defaultInstance = com.echo.protocol.captain.AddUserAddressRequest(
  )
  implicit class AddUserAddressRequestLens[UpperPB](_l: com.trueaccord.lenses.Lens[UpperPB, com.echo.protocol.captain.AddUserAddressRequest]) extends com.trueaccord.lenses.ObjectLens[UpperPB, com.echo.protocol.captain.AddUserAddressRequest](_l) {
    def token: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.token)((c_, f_) => c_.copy(token = f_))
    def recipientsName: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.recipientsName)((c_, f_) => c_.copy(recipientsName = f_))
    def recipientsPhone: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.recipientsPhone)((c_, f_) => c_.copy(recipientsPhone = f_))
    def recipientsAddress: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.recipientsAddress)((c_, f_) => c_.copy(recipientsAddress = f_))
    def recipientsPostcode: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.recipientsPostcode)((c_, f_) => c_.copy(recipientsPostcode = f_))
  }
  final val TOKEN_FIELD_NUMBER = 1
  final val RECIPIENTS_NAME_FIELD_NUMBER = 3
  final val RECIPIENTS_PHONE_FIELD_NUMBER = 4
  final val RECIPIENTS_ADDRESS_FIELD_NUMBER = 5
  final val RECIPIENTS_POSTCODE_FIELD_NUMBER = 6
}
