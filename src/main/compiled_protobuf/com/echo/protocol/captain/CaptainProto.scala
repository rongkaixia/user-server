// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.echo.protocol.captain



object CaptainProto {
  lazy val descriptor: com.google.protobuf.Descriptors.FileDescriptor = {
    val proto = com.google.protobuf.DescriptorProtos.FileDescriptorProto.parseFrom(
      com.trueaccord.scalapb.Encoding.fromBase64(scala.collection.Seq(
  """Cg1jYXB0YWluLnByb3RvEhljb20uZWNoby5wcm90b2NvbC5jYXB0YWluGgxjb21tb24ucHJvdG8i4AEKC1VzZXJBZGRyZXNzE
  h0KCmFkZHJlc3NfaWQYASABKAlSCWFkZHJlc3NJZBInCg9yZWNpcGllbnRzX25hbWUYAiABKAlSDnJlY2lwaWVudHNOYW1lEikKE
  HJlY2lwaWVudHNfcGhvbmUYAyABKAlSD3JlY2lwaWVudHNQaG9uZRItChJyZWNpcGllbnRzX2FkZHJlc3MYBCABKAlSEXJlY2lwa
  WVudHNBZGRyZXNzEi8KE3JlY2lwaWVudHNfcG9zdGNvZGUYBSABKAlSEnJlY2lwaWVudHNQb3N0Y29kZSJKChRTZWN1cml0eVF1Z
  XN0aW9uUGFpchIaCghxdWVzdGlvbhgBIAEoCVIIcXVlc3Rpb24SFgoGYW5zd2VyGAIgASgJUgZhbnN3ZXIi1wMKCFVzZXJJbmZvE
  hcKB3VzZXJfaWQYASABKAlSBnVzZXJJZBIaCgh1c2VybmFtZRgCIAEoCVIIdXNlcm5hbWUSFAoFZW1haWwYAyABKAlSBWVtYWlsE
  hoKCHBob25lbnVtGAQgASgJUghwaG9uZW51bRJeChJzZWN1cml0eV9xdWVzdGlvbjEYBSABKAsyLy5jb20uZWNoby5wcm90b2Nvb
  C5jYXB0YWluLlNlY3VyaXR5UXVlc3Rpb25QYWlyUhFzZWN1cml0eVF1ZXN0aW9uMRJeChJzZWN1cml0eV9xdWVzdGlvbjIYBiABK
  AsyLy5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLlNlY3VyaXR5UXVlc3Rpb25QYWlyUhFzZWN1cml0eVF1ZXN0aW9uMhJeChJzZ
  WN1cml0eV9xdWVzdGlvbjMYByABKAsyLy5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLlNlY3VyaXR5UXVlc3Rpb25QYWlyUhFzZ
  WN1cml0eVF1ZXN0aW9uMxJECglhZGRyZXNzZXMYCCADKAsyJi5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLlVzZXJBZGRyZXNzU
  glhZGRyZXNzZXMiRwoNU2lnbnVwUmVxdWVzdBIaCghwaG9uZW51bRgBIAEoCVIIcGhvbmVudW0SGgoIcGFzc3dvcmQYAiABKAlSC
  HBhc3N3b3JkImgKDExvZ2luUmVxdWVzdBIcCghwaG9uZW51bRgBIAEoCUgAUghwaG9uZW51bRIWCgVlbWFpbBgCIAEoCUgAUgVlb
  WFpbBIaCghwYXNzd29yZBgKIAEoCVIIcGFzc3dvcmRCBgoEbmFtZSIjCgtBdXRoUmVxdWVzdBIUCgV0b2tlbhgBIAEoCVIFdG9rZ
  W4iJQoNTG9nb3V0UmVxdWVzdBIUCgV0b2tlbhgBIAEoCVIFdG9rZW4iRAoUUXVlcnlVc2VySW5mb1JlcXVlc3QSFAoFdG9rZW4YA
  SABKAlSBXRva2VuEhYKBmZpZWxkcxgCIAMoCVIGZmllbGRzIpsDChVVcGRhdGVVc2VySW5mb1JlcXVlc3QSFAoFdG9rZW4YASABK
  AlSBXRva2VuEhoKCHVzZXJuYW1lGAIgASgJUgh1c2VybmFtZRIUCgVlbWFpbBgDIAEoCVIFZW1haWwSGgoIcGhvbmVudW0YBCABK
  AlSCHBob25lbnVtEl4KEnNlY3VyaXR5X3F1ZXN0aW9uMRgFIAEoCzIvLmNvbS5lY2hvLnByb3RvY29sLmNhcHRhaW4uU2VjdXJpd
  HlRdWVzdGlvblBhaXJSEXNlY3VyaXR5UXVlc3Rpb24xEl4KEnNlY3VyaXR5X3F1ZXN0aW9uMhgGIAEoCzIvLmNvbS5lY2hvLnByb
  3RvY29sLmNhcHRhaW4uU2VjdXJpdHlRdWVzdGlvblBhaXJSEXNlY3VyaXR5UXVlc3Rpb24yEl4KEnNlY3VyaXR5X3F1ZXN0aW9uM
  xgHIAEoCzIvLmNvbS5lY2hvLnByb3RvY29sLmNhcHRhaW4uU2VjdXJpdHlRdWVzdGlvblBhaXJSEXNlY3VyaXR5UXVlc3Rpb24zI
  uEBChVBZGRVc2VyQWRkcmVzc1JlcXVlc3QSFAoFdG9rZW4YASABKAlSBXRva2VuEicKD3JlY2lwaWVudHNfbmFtZRgDIAEoCVIOc
  mVjaXBpZW50c05hbWUSKQoQcmVjaXBpZW50c19waG9uZRgEIAEoCVIPcmVjaXBpZW50c1Bob25lEi0KEnJlY2lwaWVudHNfYWRkc
  mVzcxgFIAEoCVIRcmVjaXBpZW50c0FkZHJlc3MSLwoTcmVjaXBpZW50c19wb3N0Y29kZRgGIAEoCVIScmVjaXBpZW50c1Bvc3Rjb
  2RlIoMCChhVcGRhdGVVc2VyQWRkcmVzc1JlcXVlc3QSFAoFdG9rZW4YASABKAlSBXRva2VuEh0KCmFkZHJlc3NfaWQYAiABKAlSC
  WFkZHJlc3NJZBInCg9yZWNpcGllbnRzX25hbWUYAyABKAlSDnJlY2lwaWVudHNOYW1lEikKEHJlY2lwaWVudHNfcGhvbmUYBCABK
  AlSD3JlY2lwaWVudHNQaG9uZRItChJyZWNpcGllbnRzX2FkZHJlc3MYBSABKAlSEXJlY2lwaWVudHNBZGRyZXNzEi8KE3JlY2lwa
  WVudHNfcG9zdGNvZGUYBiABKAlSEnJlY2lwaWVudHNQb3N0Y29kZSJPChhEZWxldGVVc2VyQWRkcmVzc1JlcXVlc3QSFAoFdG9rZ
  W4YASABKAlSBXRva2VuEh0KCmFkZHJlc3NfaWQYAiABKAlSCWFkZHJlc3NJZCK8AQoOU2lnbnVwUmVzcG9uc2USQAoGaGVhZGVyG
  AEgASgLMiguY29tLmVjaG8ucHJvdG9jb2wuY29tbW9uLlJlc3BvbnNlSGVhZGVyUgZoZWFkZXISFAoFdG9rZW4YAiABKAlSBXRva
  2VuEh0KCmV4cGlyZXNfaW4YAyABKAVSCWV4cGlyZXNJbhIXCgd1c2VyX2lkGAQgASgJUgZ1c2VySWQSGgoIdXNlcm5hbWUYBSABK
  AlSCHVzZXJuYW1lIrsBCg1Mb2dpblJlc3BvbnNlEkAKBmhlYWRlchgBIAEoCzIoLmNvbS5lY2hvLnByb3RvY29sLmNvbW1vbi5SZ
  XNwb25zZUhlYWRlclIGaGVhZGVyEhQKBXRva2VuGAIgASgJUgV0b2tlbhIdCgpleHBpcmVzX2luGAMgASgFUglleHBpcmVzSW4SF
  woHdXNlcl9pZBgEIAEoCVIGdXNlcklkEhoKCHVzZXJuYW1lGAUgASgJUgh1c2VybmFtZSLDAQoMQXV0aFJlc3BvbnNlEkAKBmhlY
  WRlchgBIAEoCzIoLmNvbS5lY2hvLnByb3RvY29sLmNvbW1vbi5SZXNwb25zZUhlYWRlclIGaGVhZGVyEh0KCmlzX2V4cGlyZWQYA
  iABKAhSCWlzRXhwaXJlZBIdCgpleHBpcmVzX2luGAMgASgFUglleHBpcmVzSW4SFwoHdXNlcl9pZBgEIAEoCVIGdXNlcklkEhoKC
  HVzZXJuYW1lGAUgASgJUgh1c2VybmFtZSJSCg5Mb2dvdXRSZXNwb25zZRJACgZoZWFkZXIYASABKAsyKC5jb20uZWNoby5wcm90b
  2NvbC5jb21tb24uUmVzcG9uc2VIZWFkZXJSBmhlYWRlciKbAQoVUXVlcnlVc2VySW5mb1Jlc3BvbnNlEkAKBmhlYWRlchgBIAEoC
  zIoLmNvbS5lY2hvLnByb3RvY29sLmNvbW1vbi5SZXNwb25zZUhlYWRlclIGaGVhZGVyEkAKCXVzZXJfaW5mbxgCIAEoCzIjLmNvb
  S5lY2hvLnByb3RvY29sLmNhcHRhaW4uVXNlckluZm9SCHVzZXJJbmZvIloKFlVwZGF0ZVVzZXJJbmZvUmVzcG9uc2USQAoGaGVhZ
  GVyGAEgASgLMiguY29tLmVjaG8ucHJvdG9jb2wuY29tbW9uLlJlc3BvbnNlSGVhZGVyUgZoZWFkZXIiWgoWQWRkVXNlckFkZHJlc
  3NSZXNwb25zZRJACgZoZWFkZXIYASABKAsyKC5jb20uZWNoby5wcm90b2NvbC5jb21tb24uUmVzcG9uc2VIZWFkZXJSBmhlYWRlc
  iJdChlVcGRhdGVVc2VyQWRkcmVzc1Jlc3BvbnNlEkAKBmhlYWRlchgBIAEoCzIoLmNvbS5lY2hvLnByb3RvY29sLmNvbW1vbi5SZ
  XNwb25zZUhlYWRlclIGaGVhZGVyIl0KGURlbGV0ZVVzZXJBZGRyZXNzUmVzcG9uc2USQAoGaGVhZGVyGAEgASgLMiguY29tLmVja
  G8ucHJvdG9jb2wuY29tbW9uLlJlc3BvbnNlSGVhZGVyUgZoZWFkZXIqdQoJTG9naW5UeXBlEhQKEExPR0lOX1RZUEVfRU1QVFkQA
  BIVChFMT0dJTl9CWV9QSE9ORU5VTRABEhIKDkxPR0lOX0JZX0VNQUlMEAISEwoPTE9HSU5fQllfV0VDSEFUEAMSEgoOTE9HSU5fQ
  llfV0VJQk8QBCpBCghBdXRoVHlwZRITCg9BVVRIX1RZUEVfRU1QVFkQABIJCgVMT0NBTBABEgoKBldFQ0hBVBACEgkKBVdFSUJPE
  AMy+QcKDkNhcHRhaW5TZXJ2aWNlEl8KBlNpZ251cBIoLmNvbS5lY2hvLnByb3RvY29sLmNhcHRhaW4uU2lnbnVwUmVxdWVzdBopL
  mNvbS5lY2hvLnByb3RvY29sLmNhcHRhaW4uU2lnbnVwUmVzcG9uc2UiABJcCgVMb2dpbhInLmNvbS5lY2hvLnByb3RvY29sLmNhc
  HRhaW4uTG9naW5SZXF1ZXN0GiguY29tLmVjaG8ucHJvdG9jb2wuY2FwdGFpbi5Mb2dpblJlc3BvbnNlIgASXwoGTG9nb3V0EiguY
  29tLmVjaG8ucHJvdG9jb2wuY2FwdGFpbi5Mb2dvdXRSZXF1ZXN0GikuY29tLmVjaG8ucHJvdG9jb2wuY2FwdGFpbi5Mb2dvdXRSZ
  XNwb25zZSIAElkKBEF1dGgSJi5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLkF1dGhSZXF1ZXN0GicuY29tLmVjaG8ucHJvdG9jb
  2wuY2FwdGFpbi5BdXRoUmVzcG9uc2UiABJ0Cg1RdWVyeVVzZXJJbmZvEi8uY29tLmVjaG8ucHJvdG9jb2wuY2FwdGFpbi5RdWVye
  VVzZXJJbmZvUmVxdWVzdBowLmNvbS5lY2hvLnByb3RvY29sLmNhcHRhaW4uUXVlcnlVc2VySW5mb1Jlc3BvbnNlIgASdwoOVXBkY
  XRlVXNlckluZm8SMC5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLlVwZGF0ZVVzZXJJbmZvUmVxdWVzdBoxLmNvbS5lY2hvLnByb
  3RvY29sLmNhcHRhaW4uVXBkYXRlVXNlckluZm9SZXNwb25zZSIAEncKDkFkZFVzZXJBZGRyZXNzEjAuY29tLmVjaG8ucHJvdG9jb
  2wuY2FwdGFpbi5BZGRVc2VyQWRkcmVzc1JlcXVlc3QaMS5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLkFkZFVzZXJBZGRyZXNzU
  mVzcG9uc2UiABKAAQoRVXBkYXRlVXNlckFkZHJlc3MSMy5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLlVwZGF0ZVVzZXJBZGRyZ
  XNzUmVxdWVzdBo0LmNvbS5lY2hvLnByb3RvY29sLmNhcHRhaW4uVXBkYXRlVXNlckFkZHJlc3NSZXNwb25zZSIAEoABChFEZWxld
  GVVc2VyQWRkcmVzcxIzLmNvbS5lY2hvLnByb3RvY29sLmNhcHRhaW4uRGVsZXRlVXNlckFkZHJlc3NSZXF1ZXN0GjQuY29tLmVja
  G8ucHJvdG9jb2wuY2FwdGFpbi5EZWxldGVVc2VyQWRkcmVzc1Jlc3BvbnNlIgBiBnByb3RvMw=="""
      ).mkString))
    com.google.protobuf.Descriptors.FileDescriptor.buildFrom(proto, Array(
  com.echo.protocol.common.CommonProto.descriptor
    ))
  }
}