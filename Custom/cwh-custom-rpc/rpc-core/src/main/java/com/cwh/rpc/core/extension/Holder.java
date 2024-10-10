package com.cwh.rpc.core.extension;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 19:12
 * @Version 1.0
 * @ClassName Holder
 * @Description Holder 类，作用是为不可变的对象引用提供一个可变的包装
 */
public class Holder<T> {

    private volatile T value;

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }

}
