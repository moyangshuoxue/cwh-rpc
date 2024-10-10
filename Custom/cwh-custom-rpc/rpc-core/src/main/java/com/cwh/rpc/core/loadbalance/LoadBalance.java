package com.cwh.rpc.core.loadbalance;

import com.cwh.rpc.core.common.RpcRequest;
import com.cwh.rpc.core.common.ServiceInfo;
import com.cwh.rpc.core.extension.SPI;

import java.util.List;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 9:31
 * @Version 1.0
 * @ClassName LoadBalance
 * @Description This is a general-purpose Java class.
 */
@SPI
public interface LoadBalance {

    /**
     * 负载均衡，从传入的服务列表中按照指定的策略返回一个
     *
     * @param invokers 服务列表
     * @param request rpc请求
     * @return 按策略返回的服务信息对象
     */
    ServiceInfo select(List<ServiceInfo> invokers, RpcRequest request);

}

