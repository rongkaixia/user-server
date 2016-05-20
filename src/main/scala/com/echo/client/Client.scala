package com.echo.common

import com.datastax.driver.core._
import com.datastax.driver.core.policies._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import java.time.Instant

import scala.async.Async.{async, await}
import scala.concurrent.{ Future, Promise }
import scala.concurrent._
import scala.util.{Success, Failure}
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

import com.datastax.driver.core._
import com.google.common.util.concurrent.{FutureCallback, Futures}

class CassandraClient(val config: com.typesafe.config.Config){
  private val cluster = build
  private var session: Option[Session] = None

  private def build(): Cluster = {
    val host = config.getString("host")
    val port = config.getInt("port")
    Cluster.builder()
           .addContactPoint(host)
           .withPort(port)
           .withQueryOptions(new QueryOptions().setConsistencyLevel(ConsistencyLevel.ONE))
           .withRetryPolicy(DefaultRetryPolicy.INSTANCE)
           .build();
  }

  def connect(): this.type = {
    session = Some(cluster.connect())
    this
  }

  def getCluster(): Cluster = {
    cluster
  }

  def getSession(): Session = {
    if (!session.isDefined){
      throw new RuntimeException("Session is not defined, make sure you had call connect() method")
    }
    session.get
  }

  def shutdown(): this.type = {
    if (!session.isDefined){
      session.get.close
      session = None
    }
    this
  }
}

object CassandraClient{
  def apply(config: com.typesafe.config.Config) = new CassandraClient(config)
  
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