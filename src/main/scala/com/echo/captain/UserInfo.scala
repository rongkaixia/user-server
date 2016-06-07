package com.echo.captain

import java.util.UUID

case class UserInfo(
  id: UUID,
  username: String,
  firstname: String,
  lastname: String,
  email: String,
  phonenum: String
)