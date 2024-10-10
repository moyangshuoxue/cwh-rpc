package com.cwh.rpc.core.protocol;

import lombok.Data;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 10:33
 * @Version 1.0
 * @ClassName RpcMessage
 */
@Data
public class RpcMessage {

    /**
     * 请求头 - 协议信息
     */

    private MessageHeader header;

    /**
     * 消息体
     */

    private  Object body;
}
