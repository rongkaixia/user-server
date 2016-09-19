// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.echo.protocol.captain



@SerialVersionUID(0L)
final case class LoginResponse(
    header: scala.Option[com.echo.protocol.common.ResponseHeader] = None,
    token: String = "",
    expiresIn: Int = 0,
    userId: String = "",
    username: String = ""
    ) extends com.trueaccord.scalapb.GeneratedMessage with com.trueaccord.scalapb.Message[LoginResponse] with com.trueaccord.lenses.Updatable[LoginResponse] {
    @transient
    private[this] var __serializedSizeCachedValue: Int = 0
    private[this] def __computeSerializedValue(): Int = {
      var __size = 0
      if (header.isDefined) { __size += 1 + com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(header.get.serializedSize) + header.get.serializedSize }
      if (token != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(2, token) }
      if (expiresIn != 0) { __size += com.google.protobuf.CodedOutputStream.computeInt32Size(3, expiresIn) }
      if (userId != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(4, userId) }
      if (username != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(5, username) }
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
      header.foreach { __v =>
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__v.serializedSize)
        __v.writeTo(_output__)
      };
      {
        val __v = token
        if (__v != "") {
          _output__.writeString(2, __v)
        }
      };
      {
        val __v = expiresIn
        if (__v != 0) {
          _output__.writeInt32(3, __v)
        }
      };
      {
        val __v = userId
        if (__v != "") {
          _output__.writeString(4, __v)
        }
      };
      {
        val __v = username
        if (__v != "") {
          _output__.writeString(5, __v)
        }
      };
    }
    def mergeFrom(`_input__`: com.google.protobuf.CodedInputStream): com.echo.protocol.captain.LoginResponse = {
      var __header = this.header
      var __token = this.token
      var __expiresIn = this.expiresIn
      var __userId = this.userId
      var __username = this.username
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __header = Some(com.trueaccord.scalapb.LiteParser.readMessage(_input__, __header.getOrElse(com.echo.protocol.common.ResponseHeader.defaultInstance)))
          case 18 =>
            __token = _input__.readString()
          case 24 =>
            __expiresIn = _input__.readInt32()
          case 34 =>
            __userId = _input__.readString()
          case 42 =>
            __username = _input__.readString()
          case tag => _input__.skipField(tag)
        }
      }
      com.echo.protocol.captain.LoginResponse(
          header = __header,
          token = __token,
          expiresIn = __expiresIn,
          userId = __userId,
          username = __username
      )
    }
    def getHeader: com.echo.protocol.common.ResponseHeader = header.getOrElse(com.echo.protocol.common.ResponseHeader.defaultInstance)
    def clearHeader: LoginResponse = copy(header = None)
    def withHeader(__v: com.echo.protocol.common.ResponseHeader): LoginResponse = copy(header = Some(__v))
    def withToken(__v: String): LoginResponse = copy(token = __v)
    def withExpiresIn(__v: Int): LoginResponse = copy(expiresIn = __v)
    def withUserId(__v: String): LoginResponse = copy(userId = __v)
    def withUsername(__v: String): LoginResponse = copy(username = __v)
    def getField(__field: com.google.protobuf.Descriptors.FieldDescriptor): scala.Any = {
      __field.getNumber match {
        case 1 => header.getOrElse(null)
        case 2 => {
          val __t = token
          if (__t != "") __t else null
        }
        case 3 => {
          val __t = expiresIn
          if (__t != 0) __t else null
        }
        case 4 => {
          val __t = userId
          if (__t != "") __t else null
        }
        case 5 => {
          val __t = username
          if (__t != "") __t else null
        }
      }
    }
    override def toString: String = com.trueaccord.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.echo.protocol.captain.LoginResponse
}

object LoginResponse extends com.trueaccord.scalapb.GeneratedMessageCompanion[com.echo.protocol.captain.LoginResponse] {
  implicit def messageCompanion: com.trueaccord.scalapb.GeneratedMessageCompanion[com.echo.protocol.captain.LoginResponse] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[com.google.protobuf.Descriptors.FieldDescriptor, scala.Any]): com.echo.protocol.captain.LoginResponse = {
    require(__fieldsMap.keys.forall(_.getContainingType() == descriptor), "FieldDescriptor does not match message type.")
    val __fields = descriptor.getFields
    com.echo.protocol.captain.LoginResponse(
      __fieldsMap.get(__fields.get(0)).asInstanceOf[scala.Option[com.echo.protocol.common.ResponseHeader]],
      __fieldsMap.getOrElse(__fields.get(1), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(2), 0).asInstanceOf[Int],
      __fieldsMap.getOrElse(__fields.get(3), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(4), "").asInstanceOf[String]
    )
  }
  def descriptor: com.google.protobuf.Descriptors.Descriptor = CaptainProto.descriptor.getMessageTypes.get(13)
  def messageCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedMessageCompanion[_] = {
    require(__field.getContainingType() == descriptor, "FieldDescriptor does not match message type.")
    var __out: com.trueaccord.scalapb.GeneratedMessageCompanion[_] = null
    __field.getNumber match {
      case 1 => __out = com.echo.protocol.common.ResponseHeader
    }
  __out
  }
  def enumCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__field)
  lazy val defaultInstance = com.echo.protocol.captain.LoginResponse(
  )
  implicit class LoginResponseLens[UpperPB](_l: com.trueaccord.lenses.Lens[UpperPB, com.echo.protocol.captain.LoginResponse]) extends com.trueaccord.lenses.ObjectLens[UpperPB, com.echo.protocol.captain.LoginResponse](_l) {
    def header: com.trueaccord.lenses.Lens[UpperPB, com.echo.protocol.common.ResponseHeader] = field(_.getHeader)((c_, f_) => c_.copy(header = Some(f_)))
    def optionalHeader: com.trueaccord.lenses.Lens[UpperPB, scala.Option[com.echo.protocol.common.ResponseHeader]] = field(_.header)((c_, f_) => c_.copy(header = f_))
    def token: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.token)((c_, f_) => c_.copy(token = f_))
    def expiresIn: com.trueaccord.lenses.Lens[UpperPB, Int] = field(_.expiresIn)((c_, f_) => c_.copy(expiresIn = f_))
    def userId: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.userId)((c_, f_) => c_.copy(userId = f_))
    def username: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.username)((c_, f_) => c_.copy(username = f_))
  }
  final val HEADER_FIELD_NUMBER = 1
  final val TOKEN_FIELD_NUMBER = 2
  final val EXPIRES_IN_FIELD_NUMBER = 3
  final val USER_ID_FIELD_NUMBER = 4
  final val USERNAME_FIELD_NUMBER = 5
}
