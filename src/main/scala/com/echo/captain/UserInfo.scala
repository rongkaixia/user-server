package com.echo.captain

import java.util.UUID

case class UserInfo(
  id: String,
  nickname: String,
  truename: String,
  email: String,
  phonenum: String,
  securityQuestion1: String,
  securityQuestion2: String,
  securityQuestion3: String,
  securityQuestion1Ans: String,
  securityQuestion2Ans: String,
  securityQuestion3Ans: String
)