package com.cwh.rpc.client.transport.socket;

import com.cwh.rpc.client.transport.RpcClient;
import com.cwh.rpc.client.transport.http.HttpRpcClient;
import com.cwh.rpc.client.transport.netty.NettyRpcClient;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 16:58
 * @Version 1.0
 * @ClassName RpcClientFactory
 * @Description @deprecated 当前类已被弃用，使用自定义 {@link com.cwh.rpc.core.extension.ExtensionLoader} 机制去动态加载 RpcClient 实现类
 */
@Deprecated
public class RpcClientFactory {

    /**
     * 根据指定的通信协议去生成对应的 RpcClient 实现类
     *
     * @param transport 指定的通信协议
     * @return 返回指定通信协议的 Rpc 客户端实现类
     */
    public static RpcClient getRpcClient(String transport) {
        switch (transport) {
            case "netty":
                return new NettyRpcClient();
            case "http":
                return new HttpRpcClient();
            case "socket":
                return new SocketRpcClient();
            default:
                throw new IllegalArgumentException(String.format("Transport communication protocol type %s is illegal.",
                        transport));
        }
    }

}
