package com.cwh.rpc.core.util;

import com.cwh.rpc.core.enums.SerializationType;
import com.cwh.rpc.core.exception.SerializeException;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 11:20
 * @Version 1.0
 * @ClassName SerializationTypeUtil
 * @Description This is a general-purpose Java class.
 */
public class SerializationTypeUtil {

    /**
     * 通过序列化类型获取序列化算法枚举类
     *
     * @param type 类型
     * @return 枚举类型
     */
    public static SerializationType parseByType(byte type) {
        for (SerializationType serializationType : SerializationType.values()) {
            if (serializationType.getType() == type) {
                return serializationType;
            }
        }
        throw new SerializeException("当前序列化方式404");

    }
    /**
     * 通过序列化名称获取序列化算法枚举类
     *
     * @param serializeName 序列化名称
     * @return 枚举类型
     */
    public static SerializationType parseByName(String serializeName) {
        for (SerializationType serializationType : SerializationType.values()) {
            if (serializationType.name().equalsIgnoreCase(serializeName)) {
                return serializationType;
            }
        }
        throw new SerializeException("当前序列化方式404");
    }
}


