package com.cwh.rpc.core.serialzation.SerializationImpl;

import com.cwh.rpc.core.exception.SerializeException;
import com.cwh.rpc.core.serialzation.Serialization;

import java.io.*;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 11:03
 * @Version 1.0
 * @ClassName JdkSerialization
 * @Description 最简易的自带的JDK序列化算法.
 */
public class JdkSerialization implements Serialization {
    @Override
    public <T> byte[] serialize(T object) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new SerializeException("JdkSerialization failed",e);
        }

    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            T object = (T)ois.readObject();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            throw new SerializeException("JdkSerialization failed", e);
        }

    }
}
