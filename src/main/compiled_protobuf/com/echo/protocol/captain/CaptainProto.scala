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
  SABKAlSBXRva2VuEhYKBmZpZWxkcxgCIAMoCVIGZmllbGRzIrcDChVVcGRhdGVVc2VySW5mb1JlcXVlc3QSFAoFdG9rZW4YASABK
  AlSBXRva2VuEhoKCHVzZXJuYW1lGAIgASgJUgh1c2VybmFtZRIUCgVlbWFpbBgDIAEoCVIFZW1haWwSGgoIcGhvbmVudW0YBCABK
  AlSCHBob25lbnVtEhoKCHBhc3N3b3JkGAUgASgJUghwYXNzd29yZBJeChJzZWN1cml0eV9xdWVzdGlvbjEYBiABKAsyLy5jb20uZ
  WNoby5wcm90b2NvbC5jYXB0YWluLlNlY3VyaXR5UXVlc3Rpb25QYWlyUhFzZWN1cml0eVF1ZXN0aW9uMRJeChJzZWN1cml0eV9xd
  WVzdGlvbjIYByABKAsyLy5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLlNlY3VyaXR5UXVlc3Rpb25QYWlyUhFzZWN1cml0eVF1Z
  XN0aW9uMhJeChJzZWN1cml0eV9xdWVzdGlvbjMYCCABKAsyLy5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLlNlY3VyaXR5UXVlc
  3Rpb25QYWlyUhFzZWN1cml0eVF1ZXN0aW9uMyLhAQoVQWRkVXNlckFkZHJlc3NSZXF1ZXN0EhQKBXRva2VuGAEgASgJUgV0b2tlb
  hInCg9yZWNpcGllbnRzX25hbWUYAyABKAlSDnJlY2lwaWVudHNOYW1lEikKEHJlY2lwaWVudHNfcGhvbmUYBCABKAlSD3JlY2lwa
  WVudHNQaG9uZRItChJyZWNpcGllbnRzX2FkZHJlc3MYBSABKAlSEXJlY2lwaWVudHNBZGRyZXNzEi8KE3JlY2lwaWVudHNfcG9zd
  GNvZGUYBiABKAlSEnJlY2lwaWVudHNQb3N0Y29kZSKDAgoYVXBkYXRlVXNlckFkZHJlc3NSZXF1ZXN0EhQKBXRva2VuGAEgASgJU
  gV0b2tlbhIdCgphZGRyZXNzX2lkGAIgASgJUglhZGRyZXNzSWQSJwoPcmVjaXBpZW50c19uYW1lGAMgASgJUg5yZWNpcGllbnRzT
  mFtZRIpChByZWNpcGllbnRzX3Bob25lGAQgASgJUg9yZWNpcGllbnRzUGhvbmUSLQoScmVjaXBpZW50c19hZGRyZXNzGAUgASgJU
  hFyZWNpcGllbnRzQWRkcmVzcxIvChNyZWNpcGllbnRzX3Bvc3Rjb2RlGAYgASgJUhJyZWNpcGllbnRzUG9zdGNvZGUiTwoYRGVsZ
  XRlVXNlckFkZHJlc3NSZXF1ZXN0EhQKBXRva2VuGAEgASgJUgV0b2tlbhIdCgphZGRyZXNzX2lkGAIgASgJUglhZGRyZXNzSWQiv
  AEKDlNpZ251cFJlc3BvbnNlEkAKBmhlYWRlchgBIAEoCzIoLmNvbS5lY2hvLnByb3RvY29sLmNvbW1vbi5SZXNwb25zZUhlYWRlc
  lIGaGVhZGVyEhQKBXRva2VuGAIgASgJUgV0b2tlbhIdCgpleHBpcmVzX2luGAMgASgFUglleHBpcmVzSW4SFwoHdXNlcl9pZBgEI
  AEoCVIGdXNlcklkEhoKCHVzZXJuYW1lGAUgASgJUgh1c2VybmFtZSK7AQoNTG9naW5SZXNwb25zZRJACgZoZWFkZXIYASABKAsyK
  C5jb20uZWNoby5wcm90b2NvbC5jb21tb24uUmVzcG9uc2VIZWFkZXJSBmhlYWRlchIUCgV0b2tlbhgCIAEoCVIFdG9rZW4SHQoKZ
  XhwaXJlc19pbhgDIAEoBVIJZXhwaXJlc0luEhcKB3VzZXJfaWQYBCABKAlSBnVzZXJJZBIaCgh1c2VybmFtZRgFIAEoCVIIdXNlc
  m5hbWUiwwEKDEF1dGhSZXNwb25zZRJACgZoZWFkZXIYASABKAsyKC5jb20uZWNoby5wcm90b2NvbC5jb21tb24uUmVzcG9uc2VIZ
  WFkZXJSBmhlYWRlchIdCgppc19leHBpcmVkGAIgASgIUglpc0V4cGlyZWQSHQoKZXhwaXJlc19pbhgDIAEoBVIJZXhwaXJlc0luE
  hcKB3VzZXJfaWQYBCABKAlSBnVzZXJJZBIaCgh1c2VybmFtZRgFIAEoCVIIdXNlcm5hbWUiUgoOTG9nb3V0UmVzcG9uc2USQAoGa
  GVhZGVyGAEgASgLMiguY29tLmVjaG8ucHJvdG9jb2wuY29tbW9uLlJlc3BvbnNlSGVhZGVyUgZoZWFkZXIimwEKFVF1ZXJ5VXNlc
  kluZm9SZXNwb25zZRJACgZoZWFkZXIYASABKAsyKC5jb20uZWNoby5wcm90b2NvbC5jb21tb24uUmVzcG9uc2VIZWFkZXJSBmhlY
  WRlchJACgl1c2VyX2luZm8YAiABKAsyIy5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLlVzZXJJbmZvUgh1c2VySW5mbyJaChZVc
  GRhdGVVc2VySW5mb1Jlc3BvbnNlEkAKBmhlYWRlchgBIAEoCzIoLmNvbS5lY2hvLnByb3RvY29sLmNvbW1vbi5SZXNwb25zZUhlY
  WRlclIGaGVhZGVyIloKFkFkZFVzZXJBZGRyZXNzUmVzcG9uc2USQAoGaGVhZGVyGAEgASgLMiguY29tLmVjaG8ucHJvdG9jb2wuY
  29tbW9uLlJlc3BvbnNlSGVhZGVyUgZoZWFkZXIiXQoZVXBkYXRlVXNlckFkZHJlc3NSZXNwb25zZRJACgZoZWFkZXIYASABKAsyK
  C5jb20uZWNoby5wcm90b2NvbC5jb21tb24uUmVzcG9uc2VIZWFkZXJSBmhlYWRlciJdChlEZWxldGVVc2VyQWRkcmVzc1Jlc3Bvb
  nNlEkAKBmhlYWRlchgBIAEoCzIoLmNvbS5lY2hvLnByb3RvY29sLmNvbW1vbi5SZXNwb25zZUhlYWRlclIGaGVhZGVyKnUKCUxvZ
  2luVHlwZRIUChBMT0dJTl9UWVBFX0VNUFRZEAASFQoRTE9HSU5fQllfUEhPTkVOVU0QARISCg5MT0dJTl9CWV9FTUFJTBACEhMKD
  0xPR0lOX0JZX1dFQ0hBVBADEhIKDkxPR0lOX0JZX1dFSUJPEAQqQQoIQXV0aFR5cGUSEwoPQVVUSF9UWVBFX0VNUFRZEAASCQoFT
  E9DQUwQARIKCgZXRUNIQVQQAhIJCgVXRUlCTxADMvkHCg5DYXB0YWluU2VydmljZRJfCgZTaWdudXASKC5jb20uZWNoby5wcm90b
  2NvbC5jYXB0YWluLlNpZ251cFJlcXVlc3QaKS5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLlNpZ251cFJlc3BvbnNlIgASXAoFT
  G9naW4SJy5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLkxvZ2luUmVxdWVzdBooLmNvbS5lY2hvLnByb3RvY29sLmNhcHRhaW4uT
  G9naW5SZXNwb25zZSIAEl8KBkxvZ291dBIoLmNvbS5lY2hvLnByb3RvY29sLmNhcHRhaW4uTG9nb3V0UmVxdWVzdBopLmNvbS5lY
  2hvLnByb3RvY29sLmNhcHRhaW4uTG9nb3V0UmVzcG9uc2UiABJZCgRBdXRoEiYuY29tLmVjaG8ucHJvdG9jb2wuY2FwdGFpbi5Bd
  XRoUmVxdWVzdBonLmNvbS5lY2hvLnByb3RvY29sLmNhcHRhaW4uQXV0aFJlc3BvbnNlIgASdAoNUXVlcnlVc2VySW5mbxIvLmNvb
  S5lY2hvLnByb3RvY29sLmNhcHRhaW4uUXVlcnlVc2VySW5mb1JlcXVlc3QaMC5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLlF1Z
  XJ5VXNlckluZm9SZXNwb25zZSIAEncKDlVwZGF0ZVVzZXJJbmZvEjAuY29tLmVjaG8ucHJvdG9jb2wuY2FwdGFpbi5VcGRhdGVVc
  2VySW5mb1JlcXVlc3QaMS5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLlVwZGF0ZVVzZXJJbmZvUmVzcG9uc2UiABJ3Cg5BZGRVc
  2VyQWRkcmVzcxIwLmNvbS5lY2hvLnByb3RvY29sLmNhcHRhaW4uQWRkVXNlckFkZHJlc3NSZXF1ZXN0GjEuY29tLmVjaG8ucHJvd
  G9jb2wuY2FwdGFpbi5BZGRVc2VyQWRkcmVzc1Jlc3BvbnNlIgASgAEKEVVwZGF0ZVVzZXJBZGRyZXNzEjMuY29tLmVjaG8ucHJvd
  G9jb2wuY2FwdGFpbi5VcGRhdGVVc2VyQWRkcmVzc1JlcXVlc3QaNC5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLlVwZGF0ZVVzZ
  XJBZGRyZXNzUmVzcG9uc2UiABKAAQoRRGVsZXRlVXNlckFkZHJlc3MSMy5jb20uZWNoby5wcm90b2NvbC5jYXB0YWluLkRlbGV0Z
  VVzZXJBZGRyZXNzUmVxdWVzdBo0LmNvbS5lY2hvLnByb3RvY29sLmNhcHRhaW4uRGVsZXRlVXNlckFkZHJlc3NSZXNwb25zZSIAY
  gZwcm90bzM="""
      ).mkString))
    com.google.protobuf.Descriptors.FileDescriptor.buildFrom(proto, Array(
  com.echo.protocol.common.CommonProto.descriptor
    ))
  }
}