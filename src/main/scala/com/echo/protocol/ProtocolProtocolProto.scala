// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.echo.protocol



object ProtocolProtocolProto {
  lazy val descriptor: com.google.protobuf.Descriptors.FileDescriptor = {
    val proto = com.google.protobuf.DescriptorProtos.FileDescriptorProto.parseFrom(
      com.trueaccord.scalapb.Encoding.fromBase64(Seq(
  """Chdwcm90b2NvbC9wcm90b2NvbC5wcm90bxIIY29tLmVjaG8ihQEKC1VzZXJBZGRyZXNzEgoKAmlkGAEgASgJEhcKD3JlY2lwa
  WVudHNfbmFtZRgCIAEoCRIYChByZWNpcGllbnRzX3Bob25lGAMgASgJEhoKEnJlY2lwaWVudHNfYWRkcmVzcxgEIAEoCRIbChNyZ
  WNpcGllbnRzX3Bvc3Rjb2RlGAUgASgJIjgKFFNlY3VyaXR5UXVlc3Rpb25QYWlyEhAKCHF1ZXN0aW9uGAEgASgJEg4KBmFuc3dlc
  hgCIAEoCSKnDgoHUmVxdWVzdBI6Cg5zaWdudXBfcmVxdWVzdBjpByABKAsyHy5jb20uZWNoby5SZXF1ZXN0LlNpZ251cFJlcXVlc
  3RIABI4Cg1sb2dpbl9yZXF1ZXN0GOoHIAEoCzIeLmNvbS5lY2hvLlJlcXVlc3QuTG9naW5SZXF1ZXN0SAASSgoWYXV0aGVudGljY
  XRpb25fcmVxdWVzdBjrByABKAsyJy5jb20uZWNoby5SZXF1ZXN0LkF1dGhlbnRpY2F0aW9uUmVxdWVzdEgAEjoKDmxvZ291dF9yZ
  XF1ZXN0GOwHIAEoCzIfLmNvbS5lY2hvLlJlcXVlc3QuTG9nb3V0UmVxdWVzdEgAEkoKF3F1ZXJ5X3VzZXJfaW5mb19yZXF1ZXN0G
  O0HIAEoCzImLmNvbS5lY2hvLlJlcXVlc3QuUXVlcnlVc2VySW5mb1JlcXVlc3RIABJMChh1cGRhdGVfdXNlcl9pbmZvX3JlcXVlc
  3QY7gcgASgLMicuY29tLmVjaG8uUmVxdWVzdC5VcGRhdGVVc2VySW5mb1JlcXVlc3RIABJMChhhZGRfdXNlcl9hZGRyZXNzX3Jlc
  XVlc3QYzAggASgLMicuY29tLmVjaG8uUmVxdWVzdC5BZGRVc2VyQWRkcmVzc1JlcXVlc3RIABJSCht1cGRhdGVfdXNlcl9hZGRyZ
  XNzX3JlcXVlc3QYzQggASgLMiouY29tLmVjaG8uUmVxdWVzdC5VcGRhdGVVc2VyQWRkcmVzc1JlcXVlc3RIABJSChtkZWxldGVfd
  XNlcl9hZGRyZXNzX3JlcXVlc3QYzgggASgLMiouY29tLmVjaG8uUmVxdWVzdC5EZWxldGVVc2VyQWRkcmVzc1JlcXVlc3RIABozC
  g1TaWdudXBSZXF1ZXN0EhAKCHBob25lbnVtGAEgASgJEhAKCHBhc3N3b3JkGAIgASgJGk0KDExvZ2luUmVxdWVzdBISCghwaG9uZ
  W51bRgBIAEoCUgAEg8KBWVtYWlsGAIgASgJSAASEAoIcGFzc3dvcmQYCiABKAlCBgoEbmFtZRomChVBdXRoZW50aWNhdGlvblJlc
  XVlc3QSDQoFdG9rZW4YASABKAkaHgoNTG9nb3V0UmVxdWVzdBINCgV0b2tlbhgBIAEoCRq8AQoUUXVlcnlVc2VySW5mb1JlcXVlc
  3QSDQoFdG9rZW4YASABKAkSFQoNcXVlcnlfdXNlcl9pZBgCIAEoCBIWCg5xdWVyeV91c2VybmFtZRgDIAEoCBITCgtxdWVyeV9lb
  WFpbBgEIAEoCBIWCg5xdWVyeV9waG9uZW51bRgFIAEoCBIgChhxdWVyeV9zZWN1cml0eV9xdWVzdGlvbnMYBiABKAgSFwoPcXVlc
  nlfYWRkcmVzc2VzGAcgASgIGocDChVVcGRhdGVVc2VySW5mb1JlcXVlc3QSDQoFdG9rZW4YASABKAkSQAoEZGF0YRgCIAMoCzIyL
  mNvbS5lY2hvLlJlcXVlc3QuVXBkYXRlVXNlckluZm9SZXF1ZXN0LlVwZGF0ZURhdGEanAIKClVwZGF0ZURhdGESEgoIdXNlcm5hb
  WUYASABKAlIABISCghwYXNzd29yZBgCIAEoCUgAEg8KBWVtYWlsGAMgASgJSAASEgoIcGhvbmVudW0YBCABKAlIABI8ChJzZWN1c
  ml0eV9xdWVzdGlvbjEYBSABKAsyHi5jb20uZWNoby5TZWN1cml0eVF1ZXN0aW9uUGFpckgAEjwKEnNlY3VyaXR5X3F1ZXN0aW9uM
  hgGIAEoCzIeLmNvbS5lY2hvLlNlY3VyaXR5UXVlc3Rpb25QYWlySAASPAoSc2VjdXJpdHlfcXVlc3Rpb24zGAcgASgLMh4uY29tL
  mVjaG8uU2VjdXJpdHlRdWVzdGlvblBhaXJIAEIHCgV2YWx1ZRqSAQoVQWRkVXNlckFkZHJlc3NSZXF1ZXN0Eg0KBXRva2VuGAEgA
  SgJEhcKD3JlY2lwaWVudHNfbmFtZRgDIAEoCRIYChByZWNpcGllbnRzX3Bob25lGAQgASgJEhoKEnJlY2lwaWVudHNfYWRkcmVzc
  xgFIAEoCRIbChNyZWNpcGllbnRzX3Bvc3Rjb2RlGAYgASgJGqEBChhVcGRhdGVVc2VyQWRkcmVzc1JlcXVlc3QSDQoFdG9rZW4YA
  SABKAkSCgoCaWQYAiABKAkSFwoPcmVjaXBpZW50c19uYW1lGAMgASgJEhgKEHJlY2lwaWVudHNfcGhvbmUYBCABKAkSGgoScmVja
  XBpZW50c19hZGRyZXNzGAUgASgJEhsKE3JlY2lwaWVudHNfcG9zdGNvZGUYBiABKAkaNQoYRGVsZXRlVXNlckFkZHJlc3NSZXF1Z
  XN0Eg0KBXRva2VuGAEgASgJEgoKAmlkGAIgASgJQgkKB2NvbnRlbnQizwsKCFJlc3BvbnNlEiQKBnJlc3VsdBgBIAEoDjIULmNvb
  S5lY2hvLlJlc3VsdENvZGUSGQoRZXJyb3JfZGVzY3JpcHRpb24YAiABKAkSPQoPc2lnbnVwX3Jlc3BvbnNlGOkHIAEoCzIhLmNvb
  S5lY2hvLlJlc3BvbnNlLlNpZ251cFJlc3BvbnNlSAASOwoObG9naW5fcmVzcG9uc2UY6gcgASgLMiAuY29tLmVjaG8uUmVzcG9uc
  2UuTG9naW5SZXNwb25zZUgAEk0KF2F1dGhlbnRpY2F0aW9uX3Jlc3BvbnNlGOsHIAEoCzIpLmNvbS5lY2hvLlJlc3BvbnNlLkF1d
  GhlbnRpY2F0aW9uUmVzcG9uc2VIABI9Cg9sb2dvdXRfcmVzcG9uc2UY7AcgASgLMiEuY29tLmVjaG8uUmVzcG9uc2UuTG9nb3V0U
  mVzcG9uc2VIABJNChhxdWVyeV91c2VyX2luZm9fcmVzcG9uc2UY7QcgASgLMiguY29tLmVjaG8uUmVzcG9uc2UuUXVlcnlVc2VyS
  W5mb1Jlc3BvbnNlSAASTwoZdXBkYXRlX3VzZXJfaW5mb19yZXNwb25zZRjuByABKAsyKS5jb20uZWNoby5SZXNwb25zZS5VcGRhd
  GVVc2VySW5mb1Jlc3BvbnNlSAASTgoYYWRkX3VzZXJfYWRkcmVzc19yZXBvbnNlGMwIIAEoCzIpLmNvbS5lY2hvLlJlc3BvbnNlL
  kFkZFVzZXJBZGRyZXNzUmVzcG9uc2VIABJUCht1cGRhdGVfdXNlcl9hZGRyZXNzX3JlcG9uc2UYzQggASgLMiwuY29tLmVjaG8uU
  mVzcG9uc2UuVXBkYXRlVXNlckFkZHJlc3NSZXNwb25zZUgAElQKG2RlbGV0ZV91c2VyX2FkZHJlc3NfcmVwb25zZRjOCCABKAsyL
  C5jb20uZWNoby5SZXNwb25zZS5EZWxldGVVc2VyQWRkcmVzc1Jlc3BvbnNlSAAaVgoOU2lnbnVwUmVzcG9uc2USDQoFdG9rZW4YA
  iABKAkSEgoKZXhwaXJlc19pbhgDIAEoBRIPCgd1c2VyX2lkGAQgASgJEhAKCHVzZXJuYW1lGAUgASgJGlUKDUxvZ2luUmVzcG9uc
  2USDQoFdG9rZW4YAiABKAkSEgoKZXhwaXJlc19pbhgDIAEoBRIPCgd1c2VyX2lkGAQgASgJEhAKCHVzZXJuYW1lGAUgASgJGmMKF
  kF1dGhlbnRpY2F0aW9uUmVzcG9uc2USEgoKaXNfZXhwaXJlZBgCIAEoCBISCgpleHBpcmVzX2luGAMgASgFEg8KB3VzZXJfaWQYB
  CABKAkSEAoIdXNlcm5hbWUYBSABKAkaEAoOTG9nb3V0UmVzcG9uc2UavAIKFVF1ZXJ5VXNlckluZm9SZXNwb25zZRIPCgd1c2VyX
  2lkGAIgASgJEhAKCHVzZXJuYW1lGAMgASgJEg0KBWVtYWlsGAQgASgJEhAKCHBob25lbnVtGAUgASgJEjoKEnNlY3VyaXR5X3F1Z
  XN0aW9uMRgGIAEoCzIeLmNvbS5lY2hvLlNlY3VyaXR5UXVlc3Rpb25QYWlyEjoKEnNlY3VyaXR5X3F1ZXN0aW9uMhgHIAEoCzIeL
  mNvbS5lY2hvLlNlY3VyaXR5UXVlc3Rpb25QYWlyEjoKEnNlY3VyaXR5X3F1ZXN0aW9uMxgIIAEoCzIeLmNvbS5lY2hvLlNlY3Vya
  XR5UXVlc3Rpb25QYWlyEisKDGFkZHJlc3NBcnJheRgUIAMoCzIVLmNvbS5lY2hvLlVzZXJBZGRyZXNzGhgKFlVwZGF0ZVVzZXJJb
  mZvUmVzcG9uc2UaGAoWQWRkVXNlckFkZHJlc3NSZXNwb25zZRobChlVcGRhdGVVc2VyQWRkcmVzc1Jlc3BvbnNlGhsKGURlbGV0Z
  VVzZXJBZGRyZXNzUmVzcG9uc2VCCQoHY29udGVudCKGAQoHTWVzc2FnZRIjCghtc2dfdHlwZRgBIAEoDjIRLmNvbS5lY2hvLk1zZ
  1R5cGUSJQoHcmVxdWVzdBjpByABKAsyES5jb20uZWNoby5SZXF1ZXN0SAASJwoIcmVzcG9uc2UY6gcgASgLMhIuY29tLmVjaG8uU
  mVzcG9uc2VIAEIGCgRib2R5KqwBCgdNc2dUeXBlEhIKDk1TR19UWVBFX0VNUFRZEAASEgoOU0lHTlVQX1JFUVVFU1QQARIRCg1MT
  0dJTl9SRVFVRVNUEAISGgoWQVVUSEVOVElDQVRJT05fUkVRVUVTVBADEhUKD1NJR05VUF9SRVNQT05TRRCAgAQSFAoOTE9HSU5fU
  kVTUE9OU0UQgYAEEh0KF0FVVEhFTlRJQ0FUSU9OX1JFU1BPTlNFEIKABCp1CglMb2dpblR5cGUSFAoQTE9HSU5fVFlQRV9FTVBUW
  RAAEhUKEUxPR0lOX0JZX1BIT05FTlVNEAESEgoOTE9HSU5fQllfRU1BSUwQAhITCg9MT0dJTl9CWV9XRUNIQVQQAxISCg5MT0dJT
  l9CWV9XRUlCTxAEKkEKCEF1dGhUeXBlEhMKD0FVVEhfVFlQRV9FTVBUWRAAEgkKBUxPQ0FMEAESCgoGV0VDSEFUEAISCQoFV0VJQ
  k8QAyqkAwoKUmVzdWx0Q29kZRILCgdTVUNDRVNTEAASGgoVSU5URVJOQUxfU0VSVkVSX0VSUk9SEJBOEh8KGlJFUVVFU1RfUkVTT
  1VSQ0VfTk9UX0ZPVU5EEJFOEhUKEE1FVEhPRF9OT1RfQUxMT1cQkk4SFwoSTUVTU0FHRV9UWVBFX0VSUk9SEJNOEhQKD0lOVkFMS
  URfTUVTU0FHRRCUThIWChBJTlZBTElEX1BIT05FTlVNEKGcARITCg1JTlZBTElEX0VNQUlMEKKcARIeChhQSE9ORU5VTV9BTFJFQ
  URZX0VYSVNURUQQpZwBEhsKFUVNQUlMX0FMUkVBRFlfRVhJU1RFRBCmnAESFgoQSU5WQUxJRF9QQVNTV09SRBCrnAESGwoVSU5WQ
  UxJRF9TRVNTSU9OX1RPS0VOELHqARIbChVTRVNTSU9OX1RPS0VOX0VYUElSRUQQsuoBEhgKEklOVkFMSURfRlJPTV9UT0tFThDBu
  AISGAoSRVhQSVJFRF9GUk9NX1RPS0VOEMK4AhIWChBJTExFR0FMX0FSR1VNRU5UENGGA2IGcHJvdG8z"""
      ).mkString))
    com.google.protobuf.Descriptors.FileDescriptor.buildFrom(proto, Array(
    ))
  }
}