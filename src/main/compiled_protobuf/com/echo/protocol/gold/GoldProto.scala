// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.echo.protocol.gold



object GoldProto {
  lazy val descriptor: com.google.protobuf.Descriptors.FileDescriptor = {
    val proto = com.google.protobuf.DescriptorProtos.FileDescriptorProto.parseFrom(
      com.trueaccord.scalapb.Encoding.fromBase64(Seq(
  """Cgpnb2xkLnByb3RvEhZjb20uZWNoby5wcm90b2NvbC5nb2xkGgxjb21tb24ucHJvdG8izAMKDE9yZGVyUmVxdWVzdBIXCgd1c
  2VyX2lkGAEgASgJUgZ1c2VySWQSFAoFdGl0bGUYAiABKAlSBXRpdGxlEh0KCnByb2R1Y3RfaWQYAyABKAlSCXByb2R1Y3RJZBIQC
  gNudW0YBCABKAVSA251bRJACgpwYXlfbWV0aG9kGAUgASgOMiEuY29tLmVjaG8ucHJvdG9jb2wuZ29sZC5QYXlNZXRob2RSCXBhe
  U1ldGhvZBJMCg5kZWxpdmVyX21ldGhvZBgGIAEoDjIlLmNvbS5lY2hvLnByb3RvY29sLmdvbGQuRGVsaXZlck1ldGhvZFINZGVsa
  XZlck1ldGhvZBInCg9yZWNpcGllbnRzX25hbWUYByABKAlSDnJlY2lwaWVudHNOYW1lEikKEHJlY2lwaWVudHNfcGhvbmUYCCABK
  AlSD3JlY2lwaWVudHNQaG9uZRItChJyZWNpcGllbnRzX2FkZHJlc3MYCSABKAlSEXJlY2lwaWVudHNBZGRyZXNzEi8KE3JlY2lwa
  WVudHNfcG9zdGNvZGUYCiABKAlSEnJlY2lwaWVudHNQb3N0Y29kZRIYCgdjb21tZW50GAsgASgJUgdjb21tZW50IicKClBheVJlc
  XVlc3QSGQoIb3JkZXJfaWQYASABKAlSB29yZGVySWQiKgoNTm90aWZ5UmVxdWVzdBIZCghvcmRlcl9pZBgBIAEoCVIHb3JkZXJJZ
  CIuChFRdWVyeU9yZGVyUmVxdWVzdBIZCghvcmRlcl9pZBgBIAEoCVIHb3JkZXJJZCIrCg5EZWxpdmVyUmVxdWVzdBIZCghvcmRlc
  l9pZBgBIAEoCVIHb3JkZXJJZCIyChVEZWxpdmVyQ29uZmlybVJlcXVlc3QSGQoIb3JkZXJfaWQYASABKAlSB29yZGVySWQiKgoNU
  mVmdW5kUmVxdWVzdBIZCghvcmRlcl9pZBgBIAEoCVIHb3JkZXJJZCIxChRSZWZ1bmRDb25maXJtUmVxdWVzdBIZCghvcmRlcl9pZ
  BgBIAEoCVIHb3JkZXJJZCKYAQoSUXVlcnlPcmRlclJlc3BvbnNlEkAKBmhlYWRlchgBIAEoCzIoLmNvbS5lY2hvLnByb3RvY29sL
  mNvbW1vbi5SZXNwb25zZUhlYWRlclIGaGVhZGVyEkAKCm9yZGVyX2luZm8YAiABKAsyIS5jb20uZWNoby5wcm90b2NvbC5nb2xkL
  k9yZGVySW5mb1IJb3JkZXJJbmZvIpMBCg1PcmRlclJlc3BvbnNlEkAKBmhlYWRlchgBIAEoCzIoLmNvbS5lY2hvLnByb3RvY29sL
  mNvbW1vbi5SZXNwb25zZUhlYWRlclIGaGVhZGVyEkAKCm9yZGVyX2luZm8YAiABKAsyIS5jb20uZWNoby5wcm90b2NvbC5nb2xkL
  k9yZGVySW5mb1IJb3JkZXJJbmZvIk8KC1BheVJlc3BvbnNlEkAKBmhlYWRlchgBIAEoCzIoLmNvbS5lY2hvLnByb3RvY29sLmNvb
  W1vbi5SZXNwb25zZUhlYWRlclIGaGVhZGVyIlIKDk5vdGlmeVJlc3BvbnNlEkAKBmhlYWRlchgBIAEoCzIoLmNvbS5lY2hvLnByb
  3RvY29sLmNvbW1vbi5SZXNwb25zZUhlYWRlclIGaGVhZGVyIlMKD0RlbGl2ZXJSZXNwb25zZRJACgZoZWFkZXIYASABKAsyKC5jb
  20uZWNoby5wcm90b2NvbC5jb21tb24uUmVzcG9uc2VIZWFkZXJSBmhlYWRlciJaChZEZWxpdmVyQ29uZmlybVJlc3BvbnNlEkAKB
  mhlYWRlchgBIAEoCzIoLmNvbS5lY2hvLnByb3RvY29sLmNvbW1vbi5SZXNwb25zZUhlYWRlclIGaGVhZGVyIqoFCglPcmRlckluZ
  m8SGQoIb3JkZXJfaWQYASABKAlSB29yZGVySWQSFwoHdXNlcl9pZBgCIAEoCVIGdXNlcklkEhQKBXRpdGxlGAMgASgJUgV0aXRsZ
  RIdCgpwcm9kdWN0X2lkGAQgASgJUglwcm9kdWN0SWQSEAoDbnVtGAUgASgFUgNudW0SQAoKcGF5X21ldGhvZBgGIAEoDjIhLmNvb
  S5lY2hvLnByb3RvY29sLmdvbGQuUGF5TWV0aG9kUglwYXlNZXRob2QSTAoOZGVsaXZlcl9tZXRob2QYByABKA4yJS5jb20uZWNob
  y5wcm90b2NvbC5nb2xkLkRlbGl2ZXJNZXRob2RSDWRlbGl2ZXJNZXRob2QSJwoPcmVjaXBpZW50c19uYW1lGAggASgJUg5yZWNpc
  GllbnRzTmFtZRIpChByZWNpcGllbnRzX3Bob25lGAkgASgJUg9yZWNpcGllbnRzUGhvbmUSLQoScmVjaXBpZW50c19hZGRyZXNzG
  AogASgJUhFyZWNpcGllbnRzQWRkcmVzcxIvChNyZWNpcGllbnRzX3Bvc3Rjb2RlGAsgASgJUhJyZWNpcGllbnRzUG9zdGNvZGUSG
  AoHY29tbWVudBgMIAEoCVIHY29tbWVudBIUCgVwcmljZRgUIAEoAVIFcHJpY2USHQoKcmVhbF9wcmljZRgVIAEoAVIJcmVhbFBya
  WNlEhoKCGRpc2NvdW50GBYgASgBUghkaXNjb3VudBIXCgdwYXlfYW10GBcgASgBUgZwYXlBbXQSIAoMcmVhbF9wYXlfYW10GBggA
  SgBUgpyZWFsUGF5QW10EjgKBXN0YXRlGB4gASgOMiIuY29tLmVjaG8ucHJvdG9jb2wuZ29sZC5PcmRlclN0YXRlUgVzdGF0ZSo2C
  glQYXlNZXRob2QSFAoQUEFZX01FVEhPRF9FTVBUWRAAEgoKBk9OTElORRABEgcKA0NPRBACKj8KDURlbGl2ZXJNZXRob2QSGAoUR
  EVMSVZFUl9NRVRIT0RfRU1QVFkQABILCgdFWFBSRVNTEAESBwoDRFREEAIqnwEKCk9yZGVyU3RhdGUSFQoRT1JERVJfU1RBVEVfR
  U1QVFkQABIJCgVVTlBBWRABEg8KC1BBWV9TVUNDRVNTEAISDQoJUEFZX0VSUk9SEAMSCwoHREVMSVZFUhAEEhMKD0RFTElWRVJfQ
  09ORklSTRAFEgoKBlJFRlVORBAGEhIKDlJFRlVORF9DT05GSVJNEAcSDQoJQ0FOQ0VMTEVEEAgy+QMKDE9yZGVyU2VydmljZRJlC
  gpRdWVyeU9yZGVyEikuY29tLmVjaG8ucHJvdG9jb2wuZ29sZC5RdWVyeU9yZGVyUmVxdWVzdBoqLmNvbS5lY2hvLnByb3RvY29sL
  mdvbGQuUXVlcnlPcmRlclJlc3BvbnNlIgASVgoFT3JkZXISJC5jb20uZWNoby5wcm90b2NvbC5nb2xkLk9yZGVyUmVxdWVzdBolL
  mNvbS5lY2hvLnByb3RvY29sLmdvbGQuT3JkZXJSZXNwb25zZSIAElkKBk5vdGlmeRIlLmNvbS5lY2hvLnByb3RvY29sLmdvbGQuT
  m90aWZ5UmVxdWVzdBomLmNvbS5lY2hvLnByb3RvY29sLmdvbGQuTm90aWZ5UmVzcG9uc2UiABJcCgdEZWxpdmVyEiYuY29tLmVja
  G8ucHJvdG9jb2wuZ29sZC5EZWxpdmVyUmVxdWVzdBonLmNvbS5lY2hvLnByb3RvY29sLmdvbGQuRGVsaXZlclJlc3BvbnNlIgASc
  QoORGVsaXZlckNvbmZpcm0SLS5jb20uZWNoby5wcm90b2NvbC5nb2xkLkRlbGl2ZXJDb25maXJtUmVxdWVzdBouLmNvbS5lY2hvL
  nByb3RvY29sLmdvbGQuRGVsaXZlckNvbmZpcm1SZXNwb25zZSIAYgZwcm90bzM="""
      ).mkString))
    com.google.protobuf.Descriptors.FileDescriptor.buildFrom(proto, Array(
  com.echo.protocol.common.CommonProto.descriptor
    ))
  }
}