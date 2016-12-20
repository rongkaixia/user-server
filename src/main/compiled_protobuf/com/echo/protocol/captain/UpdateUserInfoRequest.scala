// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.echo.protocol.captain



@SerialVersionUID(0L)
final case class UpdateUserInfoRequest(
    token: String = "",
    username: String = "",
    email: String = "",
    phonenum: String = "",
    password: String = "",
    securityQuestion1: scala.Option[com.echo.protocol.captain.SecurityQuestionPair] = None,
    securityQuestion2: scala.Option[com.echo.protocol.captain.SecurityQuestionPair] = None,
    securityQuestion3: scala.Option[com.echo.protocol.captain.SecurityQuestionPair] = None
    ) extends com.trueaccord.scalapb.GeneratedMessage with com.trueaccord.scalapb.Message[UpdateUserInfoRequest] with com.trueaccord.lenses.Updatable[UpdateUserInfoRequest] {
    @transient
    private[this] var __serializedSizeCachedValue: Int = 0
    private[this] def __computeSerializedValue(): Int = {
      var __size = 0
      if (token != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(1, token) }
      if (username != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(2, username) }
      if (email != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(3, email) }
      if (phonenum != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(4, phonenum) }
      if (password != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(5, password) }
      if (securityQuestion1.isDefined) { __size += 1 + com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(securityQuestion1.get.serializedSize) + securityQuestion1.get.serializedSize }
      if (securityQuestion2.isDefined) { __size += 1 + com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(securityQuestion2.get.serializedSize) + securityQuestion2.get.serializedSize }
      if (securityQuestion3.isDefined) { __size += 1 + com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(securityQuestion3.get.serializedSize) + securityQuestion3.get.serializedSize }
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
        val __v = username
        if (__v != "") {
          _output__.writeString(2, __v)
        }
      };
      {
        val __v = email
        if (__v != "") {
          _output__.writeString(3, __v)
        }
      };
      {
        val __v = phonenum
        if (__v != "") {
          _output__.writeString(4, __v)
        }
      };
      {
        val __v = password
        if (__v != "") {
          _output__.writeString(5, __v)
        }
      };
      securityQuestion1.foreach { __v =>
        _output__.writeTag(6, 2)
        _output__.writeUInt32NoTag(__v.serializedSize)
        __v.writeTo(_output__)
      };
      securityQuestion2.foreach { __v =>
        _output__.writeTag(7, 2)
        _output__.writeUInt32NoTag(__v.serializedSize)
        __v.writeTo(_output__)
      };
      securityQuestion3.foreach { __v =>
        _output__.writeTag(8, 2)
        _output__.writeUInt32NoTag(__v.serializedSize)
        __v.writeTo(_output__)
      };
    }
    def mergeFrom(`_input__`: com.google.protobuf.CodedInputStream): com.echo.protocol.captain.UpdateUserInfoRequest = {
      var __token = this.token
      var __username = this.username
      var __email = this.email
      var __phonenum = this.phonenum
      var __password = this.password
      var __securityQuestion1 = this.securityQuestion1
      var __securityQuestion2 = this.securityQuestion2
      var __securityQuestion3 = this.securityQuestion3
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __token = _input__.readString()
          case 18 =>
            __username = _input__.readString()
          case 26 =>
            __email = _input__.readString()
          case 34 =>
            __phonenum = _input__.readString()
          case 42 =>
            __password = _input__.readString()
          case 50 =>
            __securityQuestion1 = Some(com.trueaccord.scalapb.LiteParser.readMessage(_input__, __securityQuestion1.getOrElse(com.echo.protocol.captain.SecurityQuestionPair.defaultInstance)))
          case 58 =>
            __securityQuestion2 = Some(com.trueaccord.scalapb.LiteParser.readMessage(_input__, __securityQuestion2.getOrElse(com.echo.protocol.captain.SecurityQuestionPair.defaultInstance)))
          case 66 =>
            __securityQuestion3 = Some(com.trueaccord.scalapb.LiteParser.readMessage(_input__, __securityQuestion3.getOrElse(com.echo.protocol.captain.SecurityQuestionPair.defaultInstance)))
          case tag => _input__.skipField(tag)
        }
      }
      com.echo.protocol.captain.UpdateUserInfoRequest(
          token = __token,
          username = __username,
          email = __email,
          phonenum = __phonenum,
          password = __password,
          securityQuestion1 = __securityQuestion1,
          securityQuestion2 = __securityQuestion2,
          securityQuestion3 = __securityQuestion3
      )
    }
    def withToken(__v: String): UpdateUserInfoRequest = copy(token = __v)
    def withUsername(__v: String): UpdateUserInfoRequest = copy(username = __v)
    def withEmail(__v: String): UpdateUserInfoRequest = copy(email = __v)
    def withPhonenum(__v: String): UpdateUserInfoRequest = copy(phonenum = __v)
    def withPassword(__v: String): UpdateUserInfoRequest = copy(password = __v)
    def getSecurityQuestion1: com.echo.protocol.captain.SecurityQuestionPair = securityQuestion1.getOrElse(com.echo.protocol.captain.SecurityQuestionPair.defaultInstance)
    def clearSecurityQuestion1: UpdateUserInfoRequest = copy(securityQuestion1 = None)
    def withSecurityQuestion1(__v: com.echo.protocol.captain.SecurityQuestionPair): UpdateUserInfoRequest = copy(securityQuestion1 = Some(__v))
    def getSecurityQuestion2: com.echo.protocol.captain.SecurityQuestionPair = securityQuestion2.getOrElse(com.echo.protocol.captain.SecurityQuestionPair.defaultInstance)
    def clearSecurityQuestion2: UpdateUserInfoRequest = copy(securityQuestion2 = None)
    def withSecurityQuestion2(__v: com.echo.protocol.captain.SecurityQuestionPair): UpdateUserInfoRequest = copy(securityQuestion2 = Some(__v))
    def getSecurityQuestion3: com.echo.protocol.captain.SecurityQuestionPair = securityQuestion3.getOrElse(com.echo.protocol.captain.SecurityQuestionPair.defaultInstance)
    def clearSecurityQuestion3: UpdateUserInfoRequest = copy(securityQuestion3 = None)
    def withSecurityQuestion3(__v: com.echo.protocol.captain.SecurityQuestionPair): UpdateUserInfoRequest = copy(securityQuestion3 = Some(__v))
    def getField(__field: com.google.protobuf.Descriptors.FieldDescriptor): scala.Any = {
      __field.getNumber match {
        case 1 => {
          val __t = token
          if (__t != "") __t else null
        }
        case 2 => {
          val __t = username
          if (__t != "") __t else null
        }
        case 3 => {
          val __t = email
          if (__t != "") __t else null
        }
        case 4 => {
          val __t = phonenum
          if (__t != "") __t else null
        }
        case 5 => {
          val __t = password
          if (__t != "") __t else null
        }
        case 6 => securityQuestion1.getOrElse(null)
        case 7 => securityQuestion2.getOrElse(null)
        case 8 => securityQuestion3.getOrElse(null)
      }
    }
    override def toString: String = com.trueaccord.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.echo.protocol.captain.UpdateUserInfoRequest
}

object UpdateUserInfoRequest extends com.trueaccord.scalapb.GeneratedMessageCompanion[com.echo.protocol.captain.UpdateUserInfoRequest] {
  implicit def messageCompanion: com.trueaccord.scalapb.GeneratedMessageCompanion[com.echo.protocol.captain.UpdateUserInfoRequest] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[com.google.protobuf.Descriptors.FieldDescriptor, scala.Any]): com.echo.protocol.captain.UpdateUserInfoRequest = {
    require(__fieldsMap.keys.forall(_.getContainingType() == descriptor), "FieldDescriptor does not match message type.")
    val __fields = descriptor.getFields
    com.echo.protocol.captain.UpdateUserInfoRequest(
      __fieldsMap.getOrElse(__fields.get(0), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(1), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(2), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(3), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(4), "").asInstanceOf[String],
      __fieldsMap.get(__fields.get(5)).asInstanceOf[scala.Option[com.echo.protocol.captain.SecurityQuestionPair]],
      __fieldsMap.get(__fields.get(6)).asInstanceOf[scala.Option[com.echo.protocol.captain.SecurityQuestionPair]],
      __fieldsMap.get(__fields.get(7)).asInstanceOf[scala.Option[com.echo.protocol.captain.SecurityQuestionPair]]
    )
  }
  def descriptor: com.google.protobuf.Descriptors.Descriptor = CaptainProto.descriptor.getMessageTypes.get(9)
  def messageCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedMessageCompanion[_] = {
    require(__field.getContainingType() == descriptor, "FieldDescriptor does not match message type.")
    var __out: com.trueaccord.scalapb.GeneratedMessageCompanion[_] = null
    __field.getNumber match {
      case 6 => __out = com.echo.protocol.captain.SecurityQuestionPair
      case 7 => __out = com.echo.protocol.captain.SecurityQuestionPair
      case 8 => __out = com.echo.protocol.captain.SecurityQuestionPair
    }
  __out
  }
  def enumCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__field)
  lazy val defaultInstance = com.echo.protocol.captain.UpdateUserInfoRequest(
  )
  implicit class UpdateUserInfoRequestLens[UpperPB](_l: com.trueaccord.lenses.Lens[UpperPB, com.echo.protocol.captain.UpdateUserInfoRequest]) extends com.trueaccord.lenses.ObjectLens[UpperPB, com.echo.protocol.captain.UpdateUserInfoRequest](_l) {
    def token: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.token)((c_, f_) => c_.copy(token = f_))
    def username: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.username)((c_, f_) => c_.copy(username = f_))
    def email: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.email)((c_, f_) => c_.copy(email = f_))
    def phonenum: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.phonenum)((c_, f_) => c_.copy(phonenum = f_))
    def password: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.password)((c_, f_) => c_.copy(password = f_))
    def securityQuestion1: com.trueaccord.lenses.Lens[UpperPB, com.echo.protocol.captain.SecurityQuestionPair] = field(_.getSecurityQuestion1)((c_, f_) => c_.copy(securityQuestion1 = Some(f_)))
    def optionalSecurityQuestion1: com.trueaccord.lenses.Lens[UpperPB, scala.Option[com.echo.protocol.captain.SecurityQuestionPair]] = field(_.securityQuestion1)((c_, f_) => c_.copy(securityQuestion1 = f_))
    def securityQuestion2: com.trueaccord.lenses.Lens[UpperPB, com.echo.protocol.captain.SecurityQuestionPair] = field(_.getSecurityQuestion2)((c_, f_) => c_.copy(securityQuestion2 = Some(f_)))
    def optionalSecurityQuestion2: com.trueaccord.lenses.Lens[UpperPB, scala.Option[com.echo.protocol.captain.SecurityQuestionPair]] = field(_.securityQuestion2)((c_, f_) => c_.copy(securityQuestion2 = f_))
    def securityQuestion3: com.trueaccord.lenses.Lens[UpperPB, com.echo.protocol.captain.SecurityQuestionPair] = field(_.getSecurityQuestion3)((c_, f_) => c_.copy(securityQuestion3 = Some(f_)))
    def optionalSecurityQuestion3: com.trueaccord.lenses.Lens[UpperPB, scala.Option[com.echo.protocol.captain.SecurityQuestionPair]] = field(_.securityQuestion3)((c_, f_) => c_.copy(securityQuestion3 = f_))
  }
  final val TOKEN_FIELD_NUMBER = 1
  final val USERNAME_FIELD_NUMBER = 2
  final val EMAIL_FIELD_NUMBER = 3
  final val PHONENUM_FIELD_NUMBER = 4
  final val PASSWORD_FIELD_NUMBER = 5
  final val SECURITY_QUESTION1_FIELD_NUMBER = 6
  final val SECURITY_QUESTION2_FIELD_NUMBER = 7
  final val SECURITY_QUESTION3_FIELD_NUMBER = 8
}
