package com.cwh.rpc.core.enums;

import com.cwh.rpc.core.common.RpcRequest;
import com.cwh.rpc.core.common.RpcResponse;
import lombok.Getter;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 12:08
 * @Version 1.0
 * @ClassName MessageType
 * @Description 消息类型枚举类 用于返还具体消息类型.
 */
public enum MessageType {

    /**
     * 类型 0 表示请求消息
     */
    REQUEST((byte) 0, RpcRequest.class),

    /**
     * 类型 1 表示响应消息
     */
    RESPONSE((byte) 1, RpcResponse.class),

    /**
     * 类型 2 表示心跳检查请求
     */
    HEARTBEAT_REQUEST((byte) 2,String.class),

    /**
     * 类型 3 表示心跳检查响应
     */
    HEARTBEAT_RESPONSE((byte) 3,String.class);

    /**
     * 消息类型，<p>
     * 0 表示 {@link com.cwh.rpc.core.common.RpcRequest}，
     * 1 表示 {@link com.cwh.rpc.core.common.RpcResponse}。
     *         <p>
     */
    @Getter
    private final byte type;
    @Getter
    private final Class clazz;

    <T> MessageType(byte type,Class<T> clazz) {
        this.type = type;
        this.clazz = clazz;
    }

    /**
     * 根据消息类型获取消息枚举类
     *
     * @param type 消息类型
     * @return 返回对应的消息枚举类型
     * @throws IllegalArgumentException 非法的消息类型
     */
}
