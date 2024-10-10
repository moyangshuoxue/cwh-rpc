package com.cwh.rpc.core.loadbalance.impl;

import com.cwh.rpc.core.common.RpcRequest;
import com.cwh.rpc.core.common.ServiceInfo;
import com.cwh.rpc.core.loadbalance.AbstractLoadBalance;

import java.util.List;
import java.util.Random;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 9:37
 * @Version 1.0
 * @ClassName RandomLoadBalance
 * @Description 随机负载均衡策略实现类
 */
public class RandomLoadBalance  extends AbstractLoadBalance {
    final Random random = new Random();
    @Override
    protected ServiceInfo doSelect(List<ServiceInfo> invokers, RpcRequest request) {
        return invokers.get(random.nextInt(invokers.size()));
    }
}
