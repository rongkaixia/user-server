package com.echo.captain

import java.util.UUID
import com.echo.protocol.UserAddress
import com.echo.protocol.SecurityQuestionPair

case class UserInfo(
  id: String = null,
  username: String = null,
  email: String = null,
  phonenum: String = null,
  securityQuestion1: SecurityQuestionPair = null,
  securityQuestion2: SecurityQuestionPair = null,
  securityQuestion3: SecurityQuestionPair = null,
  addresses: Seq[UserAddress] = null
)