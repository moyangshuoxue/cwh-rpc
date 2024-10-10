package com.cwh.rpc.core.loadbalance.impl;

import com.cwh.rpc.core.common.RpcRequest;
import com.cwh.rpc.core.common.ServiceInfo;
import com.cwh.rpc.core.loadbalance.AbstractLoadBalance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 9:39
 * @Version 1.0
 * @ClassName RoundRobinLoadBalance
 * @Description 轮询负载均衡算法
 */
public class RoundRobinLoadBalance  extends AbstractLoadBalance {
    private static  final AtomicInteger atomicInteger = new AtomicInteger(0);
    @Override
    protected ServiceInfo doSelect(List<ServiceInfo> invokers, RpcRequest request) {
        return invokers.get(getAndIncrement() % invokers.size());
    }

    /**
     * 返回当前值并加一，通过 CAS 原子更新，当当前值到达 {@link Integer#MAX_VALUE} 时，重新设值为 0
     *
     * @return 返回当前的值
     */
    public final int getAndIncrement() {
        int prev,next;
        do {
            prev = atomicInteger.get();
            next = prev ==  Integer.MAX_VALUE ? 0:prev+1;
        }while (atomicInteger.compareAndSet(prev,next));
        return prev;
    }

}
