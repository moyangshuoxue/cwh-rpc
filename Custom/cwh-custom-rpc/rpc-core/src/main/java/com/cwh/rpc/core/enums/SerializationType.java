package com.cwh.rpc.core.enums;

import lombok.Getter;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 10:58
 * @Version 1.0
 * @ClassName SerializationType
 * @Description 序列化算法枚举类 用于SerializationFactory.getSerialization() 获取指定序列化算法
 */
public enum SerializationType {

    /**
     * JDK 序列化算法
     */
    JDK((byte) 0),

    /**
     * JSON 序列化算法
     */
    JSON((byte) 1),

    /**
     * HESSIAN 序列化算法
     */
    HESSIAN((byte) 2),

    /**
     * KRYO 序列化算法
     */
    KRYO((byte) 3),

    /**
     * PROTOSTUFF 序列化算法
     */
    PROTOSTUFF((byte) 4);

    /**
     * 类型
     */

    @Getter
    private final  byte type;

    SerializationType(byte tyep){
        this.type =tyep;
    }


}
