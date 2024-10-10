package com.cwh.rpc.client.transport;

import com.alibaba.nacos.api.remote.request.RequestMeta;
import com.cwh.rpc.client.common.RequestMetadata;
import com.cwh.rpc.core.protocol.RpcMessage;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 15:07
 * @Version 1.0
 * @ClassName RpcClient
 * @Description  Rpc 客户端类，负责向服务端发起请求（远程过程调用）
 */
public interface RpcClient {

    RpcMessage sendRpcRequest(RequestMetadata requestMeta);
}
