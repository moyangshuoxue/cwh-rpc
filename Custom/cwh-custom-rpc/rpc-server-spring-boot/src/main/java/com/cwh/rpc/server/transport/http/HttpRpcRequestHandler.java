package com.cwh.rpc.server.transport.http;

import com.cwh.rpc.core.common.RpcRequest;
import com.cwh.rpc.core.common.RpcResponse;
import com.cwh.rpc.core.exception.RpcException;
import com.cwh.rpc.core.factory.SingletonFactory;
import com.cwh.rpc.server.handler.RpcRequestHandler;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 14:42
 * @Version 1.0
 * @ClassName HttpRpcRequestHandler
 * @Description 基于 HTTP 协议的 RpcRequest 处理器
 */
@Slf4j
public class HttpRpcRequestHandler {

    private final RpcRequestHandler rpcRequestHandler;

    public HttpRpcRequestHandler() {
        this.rpcRequestHandler = SingletonFactory.getInstance(RpcRequestHandler.class);
    }

    @SuppressWarnings("Duplicates")
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        try {
            ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(resp.getOutputStream());
            // 读取客户端请求
            RpcRequest request = (RpcRequest) ois.readObject();
            log.debug("The server received message is {}.", request);
            // 创建一个 RpcResponse 对象来响应客户端
            RpcResponse response = new RpcResponse();
            // 处理请求
            try {
                // 获取请求的服务对应的实例对象反射调用方法的结果
                Object result = rpcRequestHandler.handleRpcRequest(request);
                response.setReturnValue(result);
            } catch (Exception e) {
                log.error("The service [{}], the method [{}] invoke failed!", request.getServiceName(), request.getMethod());
                // 若不设置，堆栈信息过多，导致报错
                response.setExceptionValue(new RpcException("Error in remote procedure call, " + e.getMessage()));
            }
            log.debug("The response is {}.", response);
            oos.writeObject(response);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("The http server failed to handle client rpc request.", e);
        }
    }

}
