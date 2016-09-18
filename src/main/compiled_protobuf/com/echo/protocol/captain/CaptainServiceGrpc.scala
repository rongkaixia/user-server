package com.echo.protocol.captain

object CaptainServiceGrpc {

  val METHOD_SIGNUP: _root_.io.grpc.MethodDescriptor[com.echo.protocol.captain.SignupRequest, com.echo.protocol.captain.SignupResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.captain.CaptainService", "Signup"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.SignupRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.SignupResponse))
  
  val METHOD_LOGIN: _root_.io.grpc.MethodDescriptor[com.echo.protocol.captain.LoginRequest, com.echo.protocol.captain.LoginResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.captain.CaptainService", "Login"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.LoginRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.LoginResponse))
  
  val METHOD_LOGOUT: _root_.io.grpc.MethodDescriptor[com.echo.protocol.captain.LogoutRequest, com.echo.protocol.captain.LogoutResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.captain.CaptainService", "Logout"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.LogoutRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.LogoutResponse))
  
  val METHOD_AUTH: _root_.io.grpc.MethodDescriptor[com.echo.protocol.captain.AuthRequest, com.echo.protocol.captain.AuthResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.captain.CaptainService", "Auth"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.AuthRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.AuthResponse))
  
  trait CaptainService {
    def signup(request: com.echo.protocol.captain.SignupRequest): scala.concurrent.Future[com.echo.protocol.captain.SignupResponse]

    def login(request: com.echo.protocol.captain.LoginRequest): scala.concurrent.Future[com.echo.protocol.captain.LoginResponse]

    def logout(request: com.echo.protocol.captain.LogoutRequest): scala.concurrent.Future[com.echo.protocol.captain.LogoutResponse]

    def auth(request: com.echo.protocol.captain.AuthRequest): scala.concurrent.Future[com.echo.protocol.captain.AuthResponse]

  }
  
  trait CaptainServiceBlockingClient {
    def signup(request: com.echo.protocol.captain.SignupRequest): com.echo.protocol.captain.SignupResponse

    def login(request: com.echo.protocol.captain.LoginRequest): com.echo.protocol.captain.LoginResponse

    def logout(request: com.echo.protocol.captain.LogoutRequest): com.echo.protocol.captain.LogoutResponse

    def auth(request: com.echo.protocol.captain.AuthRequest): com.echo.protocol.captain.AuthResponse

  }
  
  class CaptainServiceBlockingStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[CaptainServiceBlockingStub](channel, options) with CaptainServiceBlockingClient {
    override def signup(request: com.echo.protocol.captain.SignupRequest): com.echo.protocol.captain.SignupResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_SIGNUP, options), request)
    }
    
    override def login(request: com.echo.protocol.captain.LoginRequest): com.echo.protocol.captain.LoginResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_LOGIN, options), request)
    }
    
    override def logout(request: com.echo.protocol.captain.LogoutRequest): com.echo.protocol.captain.LogoutResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_LOGOUT, options), request)
    }
    
    override def auth(request: com.echo.protocol.captain.AuthRequest): com.echo.protocol.captain.AuthResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_AUTH, options), request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): CaptainServiceBlockingStub = new CaptainServiceBlockingStub(channel, options)
  }
  
  class CaptainServiceStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[CaptainServiceStub](channel, options) with CaptainService {
    override def signup(request: com.echo.protocol.captain.SignupRequest): scala.concurrent.Future[com.echo.protocol.captain.SignupResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_SIGNUP, options), request))
    }
    
    override def login(request: com.echo.protocol.captain.LoginRequest): scala.concurrent.Future[com.echo.protocol.captain.LoginResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_LOGIN, options), request))
    }
    
    override def logout(request: com.echo.protocol.captain.LogoutRequest): scala.concurrent.Future[com.echo.protocol.captain.LogoutResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_LOGOUT, options), request))
    }
    
    override def auth(request: com.echo.protocol.captain.AuthRequest): scala.concurrent.Future[com.echo.protocol.captain.AuthResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_AUTH, options), request))
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): CaptainServiceStub = new CaptainServiceStub(channel, options)
  }
  
  def bindService(serviceImpl: CaptainService, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition =
    _root_.io.grpc.ServerServiceDefinition.builder("com.echo.protocol.captain.CaptainService")
    .addMethod(
      METHOD_SIGNUP,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.captain.SignupRequest, com.echo.protocol.captain.SignupResponse] {
        override def invoke(request: com.echo.protocol.captain.SignupRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.captain.SignupResponse]): Unit =
          serviceImpl.signup(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_LOGIN,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.captain.LoginRequest, com.echo.protocol.captain.LoginResponse] {
        override def invoke(request: com.echo.protocol.captain.LoginRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.captain.LoginResponse]): Unit =
          serviceImpl.login(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_LOGOUT,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.captain.LogoutRequest, com.echo.protocol.captain.LogoutResponse] {
        override def invoke(request: com.echo.protocol.captain.LogoutRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.captain.LogoutResponse]): Unit =
          serviceImpl.logout(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_AUTH,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.captain.AuthRequest, com.echo.protocol.captain.AuthResponse] {
        override def invoke(request: com.echo.protocol.captain.AuthRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.captain.AuthResponse]): Unit =
          serviceImpl.auth(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .build()
  
  def blockingStub(channel: _root_.io.grpc.Channel): CaptainServiceBlockingStub = new CaptainServiceBlockingStub(channel)
  
  def stub(channel: _root_.io.grpc.Channel): CaptainServiceStub = new CaptainServiceStub(channel)
}