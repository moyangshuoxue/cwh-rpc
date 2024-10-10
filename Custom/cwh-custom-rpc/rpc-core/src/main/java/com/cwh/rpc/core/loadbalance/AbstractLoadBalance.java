package com.cwh.rpc.core.loadbalance;

import com.cwh.rpc.core.common.RpcRequest;
import com.cwh.rpc.core.common.ServiceInfo;

import java.util.List;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 9:34
 * @Version 1.0
 * @ClassName AbstractLoadBalance
 * @Description 负载均衡算法抽象类，定义实现负载均衡的 doSelect 方法
 */
public abstract class AbstractLoadBalance implements LoadBalance {

    @Override
    public ServiceInfo select(List<ServiceInfo> invokers, RpcRequest request) {
        if (invokers == null || invokers.isEmpty()) {
            return null;
        }
        // 如果服务列表中只有一个服务，无需进行负载均衡，直接返回
        if (invokers.size() == 1) {
            return invokers.get(0);
        }
        // 进行负载均衡，由具体的子类实现
        return doSelect(invokers, request);
    }

    /**
     * 实现具体负载均衡策略的选择
     *
     * @param invokers 服务列表
     * @param request  rpc 请求
     * @return 返回服务信息
     */
    protected abstract ServiceInfo doSelect(List<ServiceInfo> invokers, RpcRequest request);

}

