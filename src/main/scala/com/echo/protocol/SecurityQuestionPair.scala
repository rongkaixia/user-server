// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.echo.protocol



@SerialVersionUID(0L)
final case class SecurityQuestionPair(
    question: String = "",
    answer: String = ""
    ) extends com.trueaccord.scalapb.GeneratedMessage with com.trueaccord.scalapb.Message[SecurityQuestionPair] with com.trueaccord.lenses.Updatable[SecurityQuestionPair] {
    @transient
    lazy val serializedSize: Int = {
      var __size = 0
      if (question != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(1, question) }
      if (answer != "") { __size += com.google.protobuf.CodedOutputStream.computeStringSize(2, answer) }
      __size
    }
    def writeTo(output: com.google.protobuf.CodedOutputStream): Unit = {
      {
        val __v = question
        if (__v != "") {
          output.writeString(1, __v)
        }
      };
      {
        val __v = answer
        if (__v != "") {
          output.writeString(2, __v)
        }
      };
    }
    def mergeFrom(__input: com.google.protobuf.CodedInputStream): com.echo.protocol.SecurityQuestionPair = {
      var __question = this.question
      var __answer = this.answer
      var _done__ = false
      while (!_done__) {
        val _tag__ = __input.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __question = __input.readString()
          case 18 =>
            __answer = __input.readString()
          case tag => __input.skipField(tag)
        }
      }
      com.echo.protocol.SecurityQuestionPair(
          question = __question,
          answer = __answer
      )
    }
    def withQuestion(__v: String): SecurityQuestionPair = copy(question = __v)
    def withAnswer(__v: String): SecurityQuestionPair = copy(answer = __v)
    def getField(__field: com.google.protobuf.Descriptors.FieldDescriptor): scala.Any = {
      __field.getNumber match {
        case 1 => {
          val __t = question
          if (__t != "") __t else null
        }
        case 2 => {
          val __t = answer
          if (__t != "") __t else null
        }
      }
    }
    override def toString: String = com.trueaccord.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.echo.protocol.SecurityQuestionPair
}

object SecurityQuestionPair extends com.trueaccord.scalapb.GeneratedMessageCompanion[SecurityQuestionPair] {
  implicit def messageCompanion: com.trueaccord.scalapb.GeneratedMessageCompanion[SecurityQuestionPair] = this
  def fromFieldsMap(__fieldsMap: Map[com.google.protobuf.Descriptors.FieldDescriptor, scala.Any]): com.echo.protocol.SecurityQuestionPair = {
    require(__fieldsMap.keys.forall(_.getContainingType() == descriptor), "FieldDescriptor does not match message type.")
    val __fields = descriptor.getFields
    com.echo.protocol.SecurityQuestionPair(
      __fieldsMap.getOrElse(__fields.get(0), "").asInstanceOf[String],
      __fieldsMap.getOrElse(__fields.get(1), "").asInstanceOf[String]
    )
  }
  def descriptor: com.google.protobuf.Descriptors.Descriptor = ProtocolProtocolProto.descriptor.getMessageTypes.get(1)
  def messageCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__field)
  def enumCompanionForField(__field: com.google.protobuf.Descriptors.FieldDescriptor): com.trueaccord.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__field)
  lazy val defaultInstance = com.echo.protocol.SecurityQuestionPair(
  )
  implicit class SecurityQuestionPairLens[UpperPB](_l: com.trueaccord.lenses.Lens[UpperPB, SecurityQuestionPair]) extends com.trueaccord.lenses.ObjectLens[UpperPB, SecurityQuestionPair](_l) {
    def question: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.question)((c_, f_) => c_.copy(question = f_))
    def answer: com.trueaccord.lenses.Lens[UpperPB, String] = field(_.answer)((c_, f_) => c_.copy(answer = f_))
  }
  final val QUESTION_FIELD_NUMBER = 1
  final val ANSWER_FIELD_NUMBER = 2
}
