package com.cwh.rpc.core.serialzation;

import com.cwh.rpc.core.enums.SerializationType;
import com.cwh.rpc.core.serialzation.SerializationImpl.*;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 10:47
 * @Version 1.0
 * @ClassName SerializationFactory
 * @Description This is a general-purpose Java class.
 */
public class SerializationFactory {

    public static  Serialization getSerialization(SerializationType enumType){
        switch (enumType){
            case JDK:
                return new JdkSerialization();
            case JSON:
                return new JsonSerialization();
            case HESSIAN:
                return new HessianSerialization();
            case KRYO:
                return new KryoSerialization();
            case PROTOSTUFF:
                return new ProtostuffSerialization();
            default:
                throw new IllegalArgumentException(String.format("The serialization type %s is illegal.",
                        enumType.name()));

        }

    }

}
