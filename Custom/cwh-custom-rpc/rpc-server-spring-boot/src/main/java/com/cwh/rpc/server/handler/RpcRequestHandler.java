package com.cwh.rpc.server.handler;

import com.cwh.rpc.core.common.RpcRequest;
import com.cwh.rpc.core.exception.RpcException;
import com.cwh.rpc.server.store.LocalServiceCache;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 13:44
 * @Version 1.0
 * @ClassName RpcRequestHandler
 * @DescriptionRpc 请求调用处理器
 */
@Slf4j
public class RpcRequestHandler {

    /**
     * 处理 RpcRequest
     *
     * @param request rpc request 对象
     * @return 返回方法调用结果
     * @throws Exception 反射调用方法失败，抛出异常
     */
    public Object handleRpcRequest(RpcRequest request) throws Exception {
        // 反射调用 RpcRequest 请求指定的方法
        // 获取请求服务实例
        Object service = LocalServiceCache.getService(request.getServiceName());
        if (service == null) {
            log.error("The service [{}] is not exist!", request.getServiceName());
            throw new RpcException(String.format("The service [%s] is not exist!", request.getServiceName()));
        }
        // 获取指定的方法
        Method method = service.getClass().getMethod(request.getMethod(), request.getParameterTypes());
        // 调用方法并返回结果
        return method.invoke(service, request.getParameterValues());
    }

}
