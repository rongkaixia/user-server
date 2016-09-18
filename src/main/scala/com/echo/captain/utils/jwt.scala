package com.echo.captain.utils

import com.echo.protocol.captain._
import com.echo.protocol.common._
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.impl.crypto.MacProvider

object Jwt {
  def generateToken(subject: String, jwtSecretKey: String, loginType: LoginType = null): String = {
    val token = Jwts.builder().setSubject(subject)
    .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
    .compact()

    loginType match{
      case LoginType.LOGIN_BY_WECHAT => "wechat_" + token
      case LoginType.LOGIN_BY_WEIBO => "weibo_" + token
      case _ => "local_" + token
    }
  }
}