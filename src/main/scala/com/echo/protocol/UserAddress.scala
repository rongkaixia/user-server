// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.echo.protocol



@SerialVersionUID(0L)
final case class UserAddress(
    id: String = "",
    recipientsName: String = "",
    recipientsPhone: String = "",
    recipientsAddress: String = "",
    recipientsPostcode: String = ""
    ) extends com.trueaccord.scalapb.GeneratedMessage with com.trueaccord.scalapb.Message[UserAddress] with com.trueaccord.lenses.Updatable[UserAddress] {
    @transient
    lazy val serializedSize: Int = {
      var __size = 0
      if (id != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(1, id) }
      if (recipientsName != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(2, recipientsName) }
      if (recipientsPhone != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(3, recipientsPhone) }
      if (recipientsAddress != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(4, recipientsAddress) }
      if (recipientsPostcode != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(5, recipientsPostcode) }
      __size
    }
    def writeTo(output: com.google.protobuf.CodedOutputStream): Unit = {
      {
        val __v = id
        if (__v != "") {
          output.writeString(1, __v)
        }
      };
      {
        val __v = recipientsName
        if (__v != "") {
          output.writeString(2, __v)
        }
      };
      {
        val __v = recipientsPhone
        if (__v != "") {
          output.writeString(3, __v)
        }
      };
      {
        val __v = recipientsAddress
        if (__v != "") {
          output.writeString(4, __v)
        }
      };
      {
        val __v = recipientsPostcode
        if (__v != "") {
          output.writeString(5, __v)
        }
      };
    }
    def mergeFrom(__input: com.google.protobuf.CodedInputStream): com.echo.protocol.UserAddress = {
      var __id = this.id
      var __recipientsName = this.recipientsName
      var __recipientsPhone = this.recipientsPhone
      var __recipientsAddress = this.recipientsAddress
      var __recipientsPostcode = this.recipientsPostcode
      var _done__ = false
      while (!_done__) {
        val _tag__ = __input.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __id = __input.readString()
          case 18 =>
            __recipientsName = __input.readString()
          case 26 =>
            __recipientsPhone = __input.readString()
          case 34 =>
            __recipientsAddress = __input.readString()
          case 42 =>
            __recipientsPostcode = __input.readString()
          case tag => __input.skipField(tag)
        }
      }
      com.echo.protocol.UserAddress(
          id = __id,
          recipientsName = __recipientsName,
          recipientsPhone = __recipientsPhone,
          recipientsAddress = __recipientsAddress,
          recipientsPostcode = __recipientsPostcode
      )
    }
    def withId(__v: String): UserAddress = copy(id = __v)
    def withRecipientsName(__v: String): UserAddress = copy(recipientsName = __v)
    def withRecipientsPhone(__v: String): UserAddress = copy(recipientsPhone = __v)
    def withRecipientsAddress(__v: String): UserAddress = copy(recipientsAddress = __v)
    def withRecipientsPostcode(__v: String): UserAddress = copy(recipientsPostcode = __v)
    def getField(__field: com.google.protobuf.Descriptors.FieldDescriptor): scala.Any = {
      __field.getNumber match {
        case 1 => {
          val __t = id
          if (__t != "") __t else null
        }
        case 2 => {
          val __t = recipientsName
          if (__t != "") __t else null
        }
        case 3 => {
          val __t = recipientsPhone
          if (__t != "") __t else null
        }
        case 4 => {
          val __t = recipientsAddress
          if (__t != "") __t else null
        }
        case 5 => {
          val __t = recipientsPostcode
          if (__t != "") __t else null
        }
      }
    }
    override def toString: String = com.trueaccord.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.echo.protocol.UserAddress
}

object UserAddress extends com.trueaccord.scalapb.GeneratedMessageCompanion[UserAddress] {
  implicit def messageCompanion: com.trueaccord.scalapb.GeneratedMessageCompanion[UserAddress] = this
  def fromFieldsMap(__fieldsMap: Map[com.google.protobuf.Descriptors.FieldDescriptor, scala.Any]): com.echo.protocol.UserAddress = {
    require(__fieldsMap.keys.forall(_.getContainingType() == descriptor), "FieldDescriptor does not match message type.")
    val __fields = descriptor.getFields
    com.echo.protocol.UserAddress(
      __fieldsMap.getOrElse(__fields.get(0), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(1), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(2), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(3), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(4), "").asInstanceOf[String]
    )
  }
  def descriptor: com.google.protobuf.Descriptors.Descriptor = ProtocolProtocolProto.descriptor.getMessageTypes.get(0)
  def messageCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__field)
  def enumCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__field)
  lazy val defaultInstance = com.echo.protocol.UserAddress(
  )
  implicit class UserAddressLens[UpperPB](_l: com.trueaccord.lenses.Lens[UpperPB, UserAddress]) extends com.trueaccord.lenses.ObjectLens[UpperPB, UserAddress](_l) {
    def id: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.id)((c_, f_) => c_.copy(id = f_))
    def recipientsName: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.recipientsName)((c_, f_) => c_.copy(recipientsName = f_))
    def recipientsPhone: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.recipientsPhone)((c_, f_) => c_.copy(recipientsPhone = f_))
    def recipientsAddress: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.recipientsAddress)((c_, f_) => c_.copy(recipientsAddress = f_))
    def recipientsPostcode: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.recipientsPostcode)((c_, f_) => c_.copy(recipientsPostcode = f_))
  }
  final val ID_FIELD_NUMBER = 1
  final val RECIPIENTS_NAME_FIELD_NUMBER = 2
  final val RECIPIENTS_PHONE_FIELD_NUMBER = 3
  final val RECIPIENTS_ADDRESS_FIELD_NUMBER = 4
  final val RECIPIENTS_POSTCODE_FIELD_NUMBER = 5
}
