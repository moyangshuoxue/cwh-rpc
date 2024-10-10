package com.cwh.rpc.provider.service.impl;

import com.cwh.rpc.common.service.HelloService;
import com.cwh.rpc.server.annotation.RpcService;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/10 20:06
 * @Version 1.0
 * @ClassName HelloServiceImpl
 * @Description This is a general-purpose Java class.
 */
@RpcService(interfaceClass = HelloServiceImpl.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
