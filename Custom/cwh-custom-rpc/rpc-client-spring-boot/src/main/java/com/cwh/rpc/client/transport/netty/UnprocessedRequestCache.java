package com.cwh.rpc.client.transport.netty;

import com.cwh.rpc.core.protocol.RpcMessage;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 16:57
 * @Version 1.0
 * @ClassName UnprocessedRequestCache
 * @Description 缓存并处理响应信息的功能已经由 {@link com.cwh.rpc.client.handler.RpcResponseHandler} 实现。.
 */
@Deprecated
public class UnprocessedRequestCache {

    /**
     * 缓存未处理的请求响应
     */
    private static final Map<Integer, CompletableFuture<RpcMessage>> UNPROCESSED_REQUESTS = new ConcurrentHashMap<>();

    public void processResponse() {
        // do something for processing response message
    }

}
