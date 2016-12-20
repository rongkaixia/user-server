package com.echo.protocol.gold

object OrderServiceGrpc {
  val METHOD_QUERY_ORDER: _root_.io.grpc.MethodDescriptor[com.echo.protocol.gold.QueryOrderRequest, com.echo.protocol.gold.QueryOrderResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.gold.OrderService", "QueryOrder"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.QueryOrderRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.QueryOrderResponse))
  
  val METHOD_QUERY_ORDER_WITH_USER: _root_.io.grpc.MethodDescriptor[com.echo.protocol.gold.QueryOrderWithUserRequest, com.echo.protocol.gold.QueryOrderWithUserResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.gold.OrderService", "QueryOrderWithUser"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.QueryOrderWithUserRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.QueryOrderWithUserResponse))
  
  val METHOD_ORDER: _root_.io.grpc.MethodDescriptor[com.echo.protocol.gold.OrderRequest, com.echo.protocol.gold.OrderResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.gold.OrderService", "Order"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.OrderRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.OrderResponse))
  
  val METHOD_NOTIFY: _root_.io.grpc.MethodDescriptor[com.echo.protocol.gold.NotifyRequest, com.echo.protocol.gold.NotifyResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.gold.OrderService", "Notify"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.NotifyRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.NotifyResponse))
  
  val METHOD_DELIVER: _root_.io.grpc.MethodDescriptor[com.echo.protocol.gold.DeliverRequest, com.echo.protocol.gold.DeliverResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.gold.OrderService", "Deliver"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.DeliverRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.DeliverResponse))
  
  val METHOD_DELIVER_CONFIRM: _root_.io.grpc.MethodDescriptor[com.echo.protocol.gold.DeliverConfirmRequest, com.echo.protocol.gold.DeliverConfirmResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.gold.OrderService", "DeliverConfirm"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.DeliverConfirmRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.DeliverConfirmResponse))
  
  val METHOD_CANCEL: _root_.io.grpc.MethodDescriptor[com.echo.protocol.gold.CancelRequest, com.echo.protocol.gold.CancelResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.gold.OrderService", "Cancel"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.CancelRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.CancelResponse))
  
  val METHOD_REFUND: _root_.io.grpc.MethodDescriptor[com.echo.protocol.gold.RefundRequest, com.echo.protocol.gold.RefundResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.gold.OrderService", "Refund"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.RefundRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.RefundResponse))
  
  val METHOD_REFUND_CONFIRM: _root_.io.grpc.MethodDescriptor[com.echo.protocol.gold.RefundConfirmRequest, com.echo.protocol.gold.RefundConfirmResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.gold.OrderService", "RefundConfirm"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.RefundConfirmRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.gold.RefundConfirmResponse))
  
  trait OrderService extends _root_.com.trueaccord.scalapb.grpc.AbstractService {
    override def serviceCompanion = OrderService
    def queryOrder(request: com.echo.protocol.gold.QueryOrderRequest): scala.concurrent.Future[com.echo.protocol.gold.QueryOrderResponse]
    def queryOrderWithUser(request: com.echo.protocol.gold.QueryOrderWithUserRequest): scala.concurrent.Future[com.echo.protocol.gold.QueryOrderWithUserResponse]
    def order(request: com.echo.protocol.gold.OrderRequest): scala.concurrent.Future[com.echo.protocol.gold.OrderResponse]
    def notify(request: com.echo.protocol.gold.NotifyRequest): scala.concurrent.Future[com.echo.protocol.gold.NotifyResponse]
    def deliver(request: com.echo.protocol.gold.DeliverRequest): scala.concurrent.Future[com.echo.protocol.gold.DeliverResponse]
    def deliverConfirm(request: com.echo.protocol.gold.DeliverConfirmRequest): scala.concurrent.Future[com.echo.protocol.gold.DeliverConfirmResponse]
    def cancel(request: com.echo.protocol.gold.CancelRequest): scala.concurrent.Future[com.echo.protocol.gold.CancelResponse]
    def refund(request: com.echo.protocol.gold.RefundRequest): scala.concurrent.Future[com.echo.protocol.gold.RefundResponse]
    def refundConfirm(request: com.echo.protocol.gold.RefundConfirmRequest): scala.concurrent.Future[com.echo.protocol.gold.RefundConfirmResponse]
  }
  
  object OrderService extends _root_.com.trueaccord.scalapb.grpc.ServiceCompanion[OrderService] {
    implicit def serviceCompanion: _root_.com.trueaccord.scalapb.grpc.ServiceCompanion[OrderService] = this
    def descriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = com.echo.protocol.gold.GoldProto.descriptor.getServices().get(0)
  }
  
  trait OrderServiceBlockingClient {
    def serviceCompanion = OrderService
    def queryOrder(request: com.echo.protocol.gold.QueryOrderRequest): com.echo.protocol.gold.QueryOrderResponse
    def queryOrderWithUser(request: com.echo.protocol.gold.QueryOrderWithUserRequest): com.echo.protocol.gold.QueryOrderWithUserResponse
    def order(request: com.echo.protocol.gold.OrderRequest): com.echo.protocol.gold.OrderResponse
    def notify(request: com.echo.protocol.gold.NotifyRequest): com.echo.protocol.gold.NotifyResponse
    def deliver(request: com.echo.protocol.gold.DeliverRequest): com.echo.protocol.gold.DeliverResponse
    def deliverConfirm(request: com.echo.protocol.gold.DeliverConfirmRequest): com.echo.protocol.gold.DeliverConfirmResponse
    def cancel(request: com.echo.protocol.gold.CancelRequest): com.echo.protocol.gold.CancelResponse
    def refund(request: com.echo.protocol.gold.RefundRequest): com.echo.protocol.gold.RefundResponse
    def refundConfirm(request: com.echo.protocol.gold.RefundConfirmRequest): com.echo.protocol.gold.RefundConfirmResponse
  }
  
  class OrderServiceBlockingStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[OrderServiceBlockingStub](channel, options) with OrderServiceBlockingClient {
    override def queryOrder(request: com.echo.protocol.gold.QueryOrderRequest): com.echo.protocol.gold.QueryOrderResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_QUERY_ORDER, options), request)
    }
    
    override def queryOrderWithUser(request: com.echo.protocol.gold.QueryOrderWithUserRequest): com.echo.protocol.gold.QueryOrderWithUserResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_QUERY_ORDER_WITH_USER, options), request)
    }
    
    override def order(request: com.echo.protocol.gold.OrderRequest): com.echo.protocol.gold.OrderResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_ORDER, options), request)
    }
    
    override def notify(request: com.echo.protocol.gold.NotifyRequest): com.echo.protocol.gold.NotifyResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_NOTIFY, options), request)
    }
    
    override def deliver(request: com.echo.protocol.gold.DeliverRequest): com.echo.protocol.gold.DeliverResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_DELIVER, options), request)
    }
    
    override def deliverConfirm(request: com.echo.protocol.gold.DeliverConfirmRequest): com.echo.protocol.gold.DeliverConfirmResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_DELIVER_CONFIRM, options), request)
    }
    
    override def cancel(request: com.echo.protocol.gold.CancelRequest): com.echo.protocol.gold.CancelResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_CANCEL, options), request)
    }
    
    override def refund(request: com.echo.protocol.gold.RefundRequest): com.echo.protocol.gold.RefundResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_REFUND, options), request)
    }
    
    override def refundConfirm(request: com.echo.protocol.gold.RefundConfirmRequest): com.echo.protocol.gold.RefundConfirmResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_REFUND_CONFIRM, options), request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): OrderServiceBlockingStub = new OrderServiceBlockingStub(channel, options)
  }
  
  class OrderServiceStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[OrderServiceStub](channel, options) with OrderService {
    override def queryOrder(request: com.echo.protocol.gold.QueryOrderRequest): scala.concurrent.Future[com.echo.protocol.gold.QueryOrderResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_QUERY_ORDER, options), request))
    }
    
    override def queryOrderWithUser(request: com.echo.protocol.gold.QueryOrderWithUserRequest): scala.concurrent.Future[com.echo.protocol.gold.QueryOrderWithUserResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_QUERY_ORDER_WITH_USER, options), request))
    }
    
    override def order(request: com.echo.protocol.gold.OrderRequest): scala.concurrent.Future[com.echo.protocol.gold.OrderResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_ORDER, options), request))
    }
    
    override def notify(request: com.echo.protocol.gold.NotifyRequest): scala.concurrent.Future[com.echo.protocol.gold.NotifyResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_NOTIFY, options), request))
    }
    
    override def deliver(request: com.echo.protocol.gold.DeliverRequest): scala.concurrent.Future[com.echo.protocol.gold.DeliverResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_DELIVER, options), request))
    }
    
    override def deliverConfirm(request: com.echo.protocol.gold.DeliverConfirmRequest): scala.concurrent.Future[com.echo.protocol.gold.DeliverConfirmResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_DELIVER_CONFIRM, options), request))
    }
    
    override def cancel(request: com.echo.protocol.gold.CancelRequest): scala.concurrent.Future[com.echo.protocol.gold.CancelResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_CANCEL, options), request))
    }
    
    override def refund(request: com.echo.protocol.gold.RefundRequest): scala.concurrent.Future[com.echo.protocol.gold.RefundResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_REFUND, options), request))
    }
    
    override def refundConfirm(request: com.echo.protocol.gold.RefundConfirmRequest): scala.concurrent.Future[com.echo.protocol.gold.RefundConfirmResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_REFUND_CONFIRM, options), request))
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): OrderServiceStub = new OrderServiceStub(channel, options)
  }
  
  def bindService(serviceImpl: OrderService, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition =
    _root_.io.grpc.ServerServiceDefinition.builder("com.echo.protocol.gold.OrderService")
    .addMethod(
      METHOD_QUERY_ORDER,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.gold.QueryOrderRequest, com.echo.protocol.gold.QueryOrderResponse] {
        override def invoke(request: com.echo.protocol.gold.QueryOrderRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.gold.QueryOrderResponse]): Unit =
          serviceImpl.queryOrder(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_QUERY_ORDER_WITH_USER,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.gold.QueryOrderWithUserRequest, com.echo.protocol.gold.QueryOrderWithUserResponse] {
        override def invoke(request: com.echo.protocol.gold.QueryOrderWithUserRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.gold.QueryOrderWithUserResponse]): Unit =
          serviceImpl.queryOrderWithUser(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_ORDER,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.gold.OrderRequest, com.echo.protocol.gold.OrderResponse] {
        override def invoke(request: com.echo.protocol.gold.OrderRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.gold.OrderResponse]): Unit =
          serviceImpl.order(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_NOTIFY,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.gold.NotifyRequest, com.echo.protocol.gold.NotifyResponse] {
        override def invoke(request: com.echo.protocol.gold.NotifyRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.gold.NotifyResponse]): Unit =
          serviceImpl.notify(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_DELIVER,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.gold.DeliverRequest, com.echo.protocol.gold.DeliverResponse] {
        override def invoke(request: com.echo.protocol.gold.DeliverRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.gold.DeliverResponse]): Unit =
          serviceImpl.deliver(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_DELIVER_CONFIRM,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.gold.DeliverConfirmRequest, com.echo.protocol.gold.DeliverConfirmResponse] {
        override def invoke(request: com.echo.protocol.gold.DeliverConfirmRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.gold.DeliverConfirmResponse]): Unit =
          serviceImpl.deliverConfirm(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_CANCEL,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.gold.CancelRequest, com.echo.protocol.gold.CancelResponse] {
        override def invoke(request: com.echo.protocol.gold.CancelRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.gold.CancelResponse]): Unit =
          serviceImpl.cancel(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_REFUND,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.gold.RefundRequest, com.echo.protocol.gold.RefundResponse] {
        override def invoke(request: com.echo.protocol.gold.RefundRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.gold.RefundResponse]): Unit =
          serviceImpl.refund(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_REFUND_CONFIRM,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.gold.RefundConfirmRequest, com.echo.protocol.gold.RefundConfirmResponse] {
        override def invoke(request: com.echo.protocol.gold.RefundConfirmRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.gold.RefundConfirmResponse]): Unit =
          serviceImpl.refundConfirm(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .build()
  
  def blockingStub(channel: _root_.io.grpc.Channel): OrderServiceBlockingStub = new OrderServiceBlockingStub(channel)
  
  def stub(channel: _root_.io.grpc.Channel): OrderServiceStub = new OrderServiceStub(channel)
  
  @deprecated("Use OrderServiceGrpc.OrderService.descriptor", since="ScalaPB 0.5.40")
  def descriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = com.echo.protocol.gold.GoldProto.descriptor.getServices().get(0)
  
}