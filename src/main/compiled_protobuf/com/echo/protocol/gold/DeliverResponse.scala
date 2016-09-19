// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.echo.protocol.gold



@SerialVersionUID(0L)
final case class DeliverResponse(
    header: scala.Option[com.echo.protocol.common.ResponseHeader] = None
    ) extends com.trueaccord.scalapb.GeneratedMessage with com.trueaccord.scalapb.Message[DeliverResponse] with com.trueaccord.lenses.Updatable[DeliverResponse] {
    @transient
    private[this] var __serializedSizeCachedValue: Int = 0
    private[this] def __computeSerializedValue(): Int = {
      var __size = 0
      if (header.isDefined) { __size += 1 + com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(header.get.serializedSize) + header.get.serializedSize }
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
    }
    def mergeFrom(`_input__`: com.google.protobuf.CodedInputStream): com.echo.protocol.gold.DeliverResponse = {
      var __header = this.header
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __header = Some(com.trueaccord.scalapb.LiteParser.readMessage(_input__, __header.getOrElse(com.echo.protocol.common.ResponseHeader.defaultInstance)))
          case tag => _input__.skipField(tag)
        }
      }
      com.echo.protocol.gold.DeliverResponse(
          header = __header
      )
    }
    def getHeader: com.echo.protocol.common.ResponseHeader = header.getOrElse(com.echo.protocol.common.ResponseHeader.defaultInstance)
    def clearHeader: DeliverResponse = copy(header = None)
    def withHeader(__v: com.echo.protocol.common.ResponseHeader): DeliverResponse = copy(header = Some(__v))
    def getField(__field: com.google.protobuf.Descriptors.FieldDescriptor): scala.Any = {
      __field.getNumber match {
        case 1 => header.getOrElse(null)
      }
    }
    override def toString: String = com.trueaccord.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.echo.protocol.gold.DeliverResponse
}

object DeliverResponse extends com.trueaccord.scalapb.GeneratedMessageCompanion[com.echo.protocol.gold.DeliverResponse] {
  implicit def messageCompanion: com.trueaccord.scalapb.GeneratedMessageCompanion[com.echo.protocol.gold.DeliverResponse] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[com.google.protobuf.Descriptors.FieldDescriptor, scala.Any]): com.echo.protocol.gold.DeliverResponse = {
    require(__fieldsMap.keys.forall(_.getContainingType() == descriptor), "FieldDescriptor does not match message type.")
    val __fields = descriptor.getFields
    com.echo.protocol.gold.DeliverResponse(
      __fieldsMap.get(__fields.get(0)).asInstanceOf[scala.Option[com.echo.protocol.common.ResponseHeader]]
    )
  }
  def descriptor: com.google.protobuf.Descriptors.Descriptor = GoldProto.descriptor.getMessageTypes.get(12)
  def messageCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedMessageCompanion[_] = {
    require(__field.getContainingType() == descriptor, "FieldDescriptor does not match message type.")
    var __out: com.trueaccord.scalapb.GeneratedMessageCompanion[_] = null
    __field.getNumber match {
      case 1 => __out = com.echo.protocol.common.ResponseHeader
    }
  __out
  }
  def enumCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__field)
  lazy val defaultInstance = com.echo.protocol.gold.DeliverResponse(
  )
  implicit class DeliverResponseLens[UpperPB](_l: com.trueaccord.lenses.Lens[UpperPB, com.echo.protocol.gold.DeliverResponse]) extends com.trueaccord.lenses.ObjectLens[UpperPB, com.echo.protocol.gold.DeliverResponse](_l) {
    def header: com.trueaccord.lenses.Lens[UpperPB, com.echo.protocol.common.ResponseHeader] = field(_.getHeader)((c_, f_) => c_.copy(header = Some(f_)))
    def optionalHeader: com.trueaccord.lenses.Lens[UpperPB, scala.Option[com.echo.protocol.common.ResponseHeader]] = field(_.header)((c_, f_) => c_.copy(header = f_))
  }
  final val HEADER_FIELD_NUMBER = 1
}
