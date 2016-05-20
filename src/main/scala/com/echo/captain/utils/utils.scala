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
  implicit class ResultSetFuture2ScalaFuture(f: ResultSetFuture){
    def toScalaFuture: Future[ResultSet] = {
      val promise = Promise[ResultSet]()
      Futures.addCallback(f, new FutureCallback[ResultSet](){
        override def onSuccess(res: ResultSet): Unit = {
          promise success res
        }
        override def onFailure(error: Throwable): Unit = {
          promise failure error
        }
      })
      promise.future
    }
  }

  
}