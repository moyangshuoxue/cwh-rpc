package com.cwh.rpc.core.serialzation.SerializationImpl;

import com.caucho.hessian.io.HessianSerializerInput;
import com.caucho.hessian.io.HessianSerializerOutput;
import com.cwh.rpc.core.exception.SerializeException;
import com.cwh.rpc.core.serialzation.Serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 11:04
 * @Version 1.0
 * @ClassName HessianSerialization
 * @Description Hessian 序列化算法
 */
public class HessianSerialization implements Serialization {
    @Override
    public <T> byte[] serialize(T object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            HessianSerializerOutput hso = new HessianSerializerOutput(baos);
            hso.writeObject(object);
            hso.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new SerializeException("Hessian serialize failed.", e);
        }
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            HessianSerializerInput hsi = new HessianSerializerInput(bis);
            return (T) hsi.readObject();
        } catch (IOException e) {
            throw new SerializeException("Hessian deserialize failed.", e);
        }
    }

}
