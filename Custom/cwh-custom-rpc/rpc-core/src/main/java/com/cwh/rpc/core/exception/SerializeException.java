package com.cwh.rpc.core.exception;

import com.cwh.rpc.core.serialzation.Serialization;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 11:06
 * @Version 1.0
 * @ClassName SerializeException
 * @Description 统一序列化异常信息处理.
 */
public class SerializeException extends   RuntimeException{
    //用于版本控制和确保序列化和反序列化的兼容性。
    private static final long serialVersionUID = 2365624081242234232L;

    public SerializeException(){
        super();
    }
    //响应异常信息
    public SerializeException(String msg) {
        super(msg);
    }
    //响应异常信息与原因
    public SerializeException(String msg, Throwable cause) {
        super(msg, cause);
    }
    //响应异常原因
    public SerializeException(Throwable cause) {
        super(cause);
    }
}
