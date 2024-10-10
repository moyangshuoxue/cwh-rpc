package com.cwh.rpc.core.exception;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 13:16
 * @Version 1.0
 * @ClassName RpcException
 * @Description 统一RPC调用异常异常信息处理..
 */
public class RpcException extends RuntimeException {

    private static final long serialVersionUID = 3365624081242234231L;

    public RpcException() {
        super();
    }

    public RpcException(String msg) {
        super(msg);
    }

    public RpcException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public RpcException(Throwable cause) {
        super(cause);
    }

}

