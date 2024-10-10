package com.cwh.rpc.common.service;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/10 19:58
 * @Version 1.0
 * @ClassName  HelloService
 * @Description 用于Consumer调用与Provider实现.
 */
public interface HelloService {
    String sayHello(String name);
}
