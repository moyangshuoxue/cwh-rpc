package com.cwh.rpc.server.transport;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 11:24
 * @Version 1.0
 * @ClassName RpcServer
 * @Description Rpc 服务类，接受客户端消息，调用客户端请求的方法并将结果返回给客户端
 */
public interface RpcServer {
    /**
     * 开启 RpcServer 服务
     *
     * @param port 启动端口
     */
    void start(Integer port);

}
