package com.echo.common

import com.datastax.driver.core._
import com.datastax.driver.core.policies._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import java.time.Instant

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
}
