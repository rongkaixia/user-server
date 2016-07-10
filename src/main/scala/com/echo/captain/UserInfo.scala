package com.echo.captain

import java.util.UUID
import com.echo.protocol.Response.QueryUserInfoResponse.AddressData

case class UserInfo(
  id: String,
  username: String,
  email: String,
  phonenum: String,
  securityQuestion1: String,
  securityQuestion2: String,
  securityQuestion3: String,
  securityQuestion1Ans: String,
  securityQuestion2Ans: String,
  securityQuestion3Ans: String,
  addresses: Seq[AddressData]
)