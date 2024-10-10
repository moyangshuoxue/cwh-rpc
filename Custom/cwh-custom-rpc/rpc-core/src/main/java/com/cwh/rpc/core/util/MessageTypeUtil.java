package com.cwh.rpc.core.util;

import com.cwh.rpc.core.enums.MessageType;
import com.cwh.rpc.core.exception.SerializeException;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 12:22
 * @Version 1.0
 * @ClassName MessageTypeUtil
 * @Description This is a general-purpose Java class.
 */
public class MessageTypeUtil {
    /**
     * 通过消息类型获取消息枚举类
     *
     * @param type 类型
     * @return 消息类型
     */
    public static MessageType parseByType(byte type) {
        for (MessageType messageType : MessageType.values()) {
            if (messageType.getType() == type) {
                return messageType;
            }
        }
        throw new IllegalArgumentException(String.format("The message type %s is illegal.", type));

    }



    /**
     * 通过消息名称获取消息算法枚举类
     *
     * @param messageName 消息名称
     * @return 枚举类型
     */
    public static MessageType parseByName(String messageName) {
        for (MessageType messageType : MessageType.values()) {
            if (messageType.name().equalsIgnoreCase(messageName)) {
                return messageType;
            }
        }
        throw new IllegalArgumentException(String.format("The message messageName %s is illegal.", messageName));
    }
}
