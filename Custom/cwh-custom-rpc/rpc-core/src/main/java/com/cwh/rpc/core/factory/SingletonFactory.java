package com.cwh.rpc.core.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 16:07
 * @Version 1.0
 * @ClassName SingletonFactory
 * @Description 获取单实例对象的工厂.
 */

public final class SingletonFactory {
    private static final Map<String, Object> OBJECT_MAP = new ConcurrentHashMap<>();

    public static <T> T getInstance(Class<T> clazz) {
        try {
            String name = clazz.getName();
            if (OBJECT_MAP.containsKey(name)) {
                return clazz.cast(OBJECT_MAP.get(name));
            } else {
                T instance = clazz.getDeclaredConstructor().newInstance();
                OBJECT_MAP.put(name, instance);
                return instance;
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}

