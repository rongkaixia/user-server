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
  
  val METHOD_QUERY_USER_INFO: _root_.io.grpc.MethodDescriptor[com.echo.protocol.captain.QueryUserInfoRequest, com.echo.protocol.captain.QueryUserInfoResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.captain.CaptainService", "QueryUserInfo"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.QueryUserInfoRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.QueryUserInfoResponse))
  
  val METHOD_UPDATE_USER_INFO: _root_.io.grpc.MethodDescriptor[com.echo.protocol.captain.UpdateUserInfoRequest, com.echo.protocol.captain.UpdateUserInfoResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.captain.CaptainService", "UpdateUserInfo"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.UpdateUserInfoRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.UpdateUserInfoResponse))
  
  val METHOD_ADD_USER_ADDRESS: _root_.io.grpc.MethodDescriptor[com.echo.protocol.captain.AddUserAddressRequest, com.echo.protocol.captain.AddUserAddressResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.captain.CaptainService", "AddUserAddress"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.AddUserAddressRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.AddUserAddressResponse))
  
  val METHOD_UPDATE_USER_ADDRESS: _root_.io.grpc.MethodDescriptor[com.echo.protocol.captain.UpdateUserAddressRequest, com.echo.protocol.captain.UpdateUserAddressResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.captain.CaptainService", "UpdateUserAddress"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.UpdateUserAddressRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.UpdateUserAddressResponse))
  
  val METHOD_DELETE_USER_ADDRESS: _root_.io.grpc.MethodDescriptor[com.echo.protocol.captain.DeleteUserAddressRequest, com.echo.protocol.captain.DeleteUserAddressResponse] =
    _root_.io.grpc.MethodDescriptor.create(
      _root_.io.grpc.MethodDescriptor.MethodType.UNARY,
      _root_.io.grpc.MethodDescriptor.generateFullMethodName("com.echo.protocol.captain.CaptainService", "DeleteUserAddress"),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.DeleteUserAddressRequest),
      new com.trueaccord.scalapb.grpc.Marshaller(com.echo.protocol.captain.DeleteUserAddressResponse))
  
  trait CaptainService extends _root_.com.trueaccord.scalapb.grpc.AbstractService {
    override def serviceCompanion = CaptainService
    def signup(request: com.echo.protocol.captain.SignupRequest): scala.concurrent.Future[com.echo.protocol.captain.SignupResponse]
    def login(request: com.echo.protocol.captain.LoginRequest): scala.concurrent.Future[com.echo.protocol.captain.LoginResponse]
    def logout(request: com.echo.protocol.captain.LogoutRequest): scala.concurrent.Future[com.echo.protocol.captain.LogoutResponse]
    def auth(request: com.echo.protocol.captain.AuthRequest): scala.concurrent.Future[com.echo.protocol.captain.AuthResponse]
    def queryUserInfo(request: com.echo.protocol.captain.QueryUserInfoRequest): scala.concurrent.Future[com.echo.protocol.captain.QueryUserInfoResponse]
    def updateUserInfo(request: com.echo.protocol.captain.UpdateUserInfoRequest): scala.concurrent.Future[com.echo.protocol.captain.UpdateUserInfoResponse]
    def addUserAddress(request: com.echo.protocol.captain.AddUserAddressRequest): scala.concurrent.Future[com.echo.protocol.captain.AddUserAddressResponse]
    def updateUserAddress(request: com.echo.protocol.captain.UpdateUserAddressRequest): scala.concurrent.Future[com.echo.protocol.captain.UpdateUserAddressResponse]
    def deleteUserAddress(request: com.echo.protocol.captain.DeleteUserAddressRequest): scala.concurrent.Future[com.echo.protocol.captain.DeleteUserAddressResponse]
  }
  
  object CaptainService extends _root_.com.trueaccord.scalapb.grpc.ServiceCompanion[CaptainService] {
    implicit def serviceCompanion: _root_.com.trueaccord.scalapb.grpc.ServiceCompanion[CaptainService] = this
    def descriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = com.echo.protocol.captain.CaptainProto.descriptor.getServices().get(0)
  }
  
  trait CaptainServiceBlockingClient {
    def serviceCompanion = CaptainService
    def signup(request: com.echo.protocol.captain.SignupRequest): com.echo.protocol.captain.SignupResponse
    def login(request: com.echo.protocol.captain.LoginRequest): com.echo.protocol.captain.LoginResponse
    def logout(request: com.echo.protocol.captain.LogoutRequest): com.echo.protocol.captain.LogoutResponse
    def auth(request: com.echo.protocol.captain.AuthRequest): com.echo.protocol.captain.AuthResponse
    def queryUserInfo(request: com.echo.protocol.captain.QueryUserInfoRequest): com.echo.protocol.captain.QueryUserInfoResponse
    def updateUserInfo(request: com.echo.protocol.captain.UpdateUserInfoRequest): com.echo.protocol.captain.UpdateUserInfoResponse
    def addUserAddress(request: com.echo.protocol.captain.AddUserAddressRequest): com.echo.protocol.captain.AddUserAddressResponse
    def updateUserAddress(request: com.echo.protocol.captain.UpdateUserAddressRequest): com.echo.protocol.captain.UpdateUserAddressResponse
    def deleteUserAddress(request: com.echo.protocol.captain.DeleteUserAddressRequest): com.echo.protocol.captain.DeleteUserAddressResponse
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
    
    override def queryUserInfo(request: com.echo.protocol.captain.QueryUserInfoRequest): com.echo.protocol.captain.QueryUserInfoResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_QUERY_USER_INFO, options), request)
    }
    
    override def updateUserInfo(request: com.echo.protocol.captain.UpdateUserInfoRequest): com.echo.protocol.captain.UpdateUserInfoResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_UPDATE_USER_INFO, options), request)
    }
    
    override def addUserAddress(request: com.echo.protocol.captain.AddUserAddressRequest): com.echo.protocol.captain.AddUserAddressResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_ADD_USER_ADDRESS, options), request)
    }
    
    override def updateUserAddress(request: com.echo.protocol.captain.UpdateUserAddressRequest): com.echo.protocol.captain.UpdateUserAddressResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_UPDATE_USER_ADDRESS, options), request)
    }
    
    override def deleteUserAddress(request: com.echo.protocol.captain.DeleteUserAddressRequest): com.echo.protocol.captain.DeleteUserAddressResponse = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_DELETE_USER_ADDRESS, options), request)
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
    
    override def queryUserInfo(request: com.echo.protocol.captain.QueryUserInfoRequest): scala.concurrent.Future[com.echo.protocol.captain.QueryUserInfoResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_QUERY_USER_INFO, options), request))
    }
    
    override def updateUserInfo(request: com.echo.protocol.captain.UpdateUserInfoRequest): scala.concurrent.Future[com.echo.protocol.captain.UpdateUserInfoResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_UPDATE_USER_INFO, options), request))
    }
    
    override def addUserAddress(request: com.echo.protocol.captain.AddUserAddressRequest): scala.concurrent.Future[com.echo.protocol.captain.AddUserAddressResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_ADD_USER_ADDRESS, options), request))
    }
    
    override def updateUserAddress(request: com.echo.protocol.captain.UpdateUserAddressRequest): scala.concurrent.Future[com.echo.protocol.captain.UpdateUserAddressResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_UPDATE_USER_ADDRESS, options), request))
    }
    
    override def deleteUserAddress(request: com.echo.protocol.captain.DeleteUserAddressRequest): scala.concurrent.Future[com.echo.protocol.captain.DeleteUserAddressResponse] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_DELETE_USER_ADDRESS, options), request))
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
    .addMethod(
      METHOD_QUERY_USER_INFO,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.captain.QueryUserInfoRequest, com.echo.protocol.captain.QueryUserInfoResponse] {
        override def invoke(request: com.echo.protocol.captain.QueryUserInfoRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.captain.QueryUserInfoResponse]): Unit =
          serviceImpl.queryUserInfo(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_UPDATE_USER_INFO,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.captain.UpdateUserInfoRequest, com.echo.protocol.captain.UpdateUserInfoResponse] {
        override def invoke(request: com.echo.protocol.captain.UpdateUserInfoRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.captain.UpdateUserInfoResponse]): Unit =
          serviceImpl.updateUserInfo(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_ADD_USER_ADDRESS,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.captain.AddUserAddressRequest, com.echo.protocol.captain.AddUserAddressResponse] {
        override def invoke(request: com.echo.protocol.captain.AddUserAddressRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.captain.AddUserAddressResponse]): Unit =
          serviceImpl.addUserAddress(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_UPDATE_USER_ADDRESS,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.captain.UpdateUserAddressRequest, com.echo.protocol.captain.UpdateUserAddressResponse] {
        override def invoke(request: com.echo.protocol.captain.UpdateUserAddressRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.captain.UpdateUserAddressResponse]): Unit =
          serviceImpl.updateUserAddress(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_DELETE_USER_ADDRESS,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[com.echo.protocol.captain.DeleteUserAddressRequest, com.echo.protocol.captain.DeleteUserAddressResponse] {
        override def invoke(request: com.echo.protocol.captain.DeleteUserAddressRequest, observer: _root_.io.grpc.stub.StreamObserver[com.echo.protocol.captain.DeleteUserAddressResponse]): Unit =
          serviceImpl.deleteUserAddress(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .build()
  
  def blockingStub(channel: _root_.io.grpc.Channel): CaptainServiceBlockingStub = new CaptainServiceBlockingStub(channel)
  
  def stub(channel: _root_.io.grpc.Channel): CaptainServiceStub = new CaptainServiceStub(channel)
  
  @deprecated("Use CaptainServiceGrpc.CaptainService.descriptor", since="ScalaPB 0.5.40")
  def descriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = com.echo.protocol.captain.CaptainProto.descriptor.getServices().get(0)
  
}