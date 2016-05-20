package com.echo.captain

import scala.async.Async.{async, await}
import scala.concurrent.{ Future, Promise }
import scala.concurrent._
import scala.util.{Success, Failure}
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

import com.datastax.driver.core._
import com.google.common.util.concurrent.{FutureCallback, Futures}

object Utils{  
}