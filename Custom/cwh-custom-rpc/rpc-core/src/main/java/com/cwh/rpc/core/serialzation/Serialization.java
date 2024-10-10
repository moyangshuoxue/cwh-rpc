package com.cwh.rpc.core.serialzation;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 10:52
 * @Version 1.0
 * @ClassName Serialization
 * @Description 不同序列化实现这个接口，方便SerializationFactory从配置文件中读取之后选择序列化算法
 */
public interface Serialization {

    /**
     * 将传入对象进行序列化
     *
     * @param object 需要被序列化的对象
     * @param <T>    对象类型
     * @return 返回序列化后的字节数组
     */
   <T>byte[] serialize(T object);

    /**
     * 将对象进行反序列化
     *
     * @param clazz 对象的类型
     * @param bytes 对象字节数组
     * @param <T>   对象类型
     * @return 返回序列化后的对象
     */
   <T> T deserialize(Class<T> clazz,byte[] bytes);
}
