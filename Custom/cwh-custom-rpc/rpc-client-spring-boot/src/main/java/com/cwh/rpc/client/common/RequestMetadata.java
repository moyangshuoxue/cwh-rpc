package com.cwh.rpc.client.common;

import com.cwh.rpc.core.protocol.RpcMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 15:11
 * @Version 1.0
 * @ClassName RequestMetadata
 * @Description 请求元数据类
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestMetadata {

    /**
     * 消息协议 - （请求头协议信息 + 请求信息）
     */
    private RpcMessage rpcMessage;

    /**
     * 远程服务提供方地址
     */
    private String serverAddr;

    /**
     * 远程服务提供方端口号
     */
    private Integer port;

    /**
     * 调用超时时间
     */
    private Integer timeout;
}
