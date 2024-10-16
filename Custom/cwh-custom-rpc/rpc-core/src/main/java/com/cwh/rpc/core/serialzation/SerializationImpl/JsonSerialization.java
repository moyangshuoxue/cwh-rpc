package com.cwh.rpc.core.serialzation.SerializationImpl;

import com.cwh.rpc.core.exception.SerializeException;
import com.cwh.rpc.core.serialzation.Serialization;
import com.google.gson.*;
import lombok.SneakyThrows;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 11:04
 * @Version 1.0
 * @ClassName
 * @Description 基于 Gson 库实现的 JSON 序列化算法类
 */
public class JsonSerialization implements Serialization {

    /**
     * 自定义 JavClass 对象序列化，解决 Gson 无法序列化 Class 信息
     */
    static class ClassCodec implements JsonSerializer<Class<?>>, JsonDeserializer<Class<?>> {
        @Override
        public JsonElement serialize(Class<?> src, Type typeOfSrc, JsonSerializationContext context) {
            // class -> json
            return new JsonPrimitive(src.getName());
        }
        @SneakyThrows
        @Override
        public Class<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String name = json.getAsString();
            return Class.forName(name);
        }


    }

    @Override
    public <T> byte[] serialize(T object) {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new ClassCodec()).create();
            String json = gson.toJson(object);
            return json.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new SerializeException("Json serialize failed.", e);
        }
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new ClassCodec()).create();
            String json = new String(bytes, StandardCharsets.UTF_8);
            return gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            throw new SerializeException("Json deserialize failed.", e);
        }
    }
}

