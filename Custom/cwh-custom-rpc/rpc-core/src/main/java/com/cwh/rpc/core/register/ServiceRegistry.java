package com.cwh.rpc.core.register;

import com.cwh.rpc.core.common.ServiceInfo;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 14:00
 * @Version 1.0
 * @ClassName ServiceRegistry
 * @Description 服务注册中心接口
 */
public interface ServiceRegistry {

    /**
     * 注册/重新注册一个服务信息到 注册中心
     *
     * @param serviceInfo 服务信息
     */
    void register(ServiceInfo serviceInfo) throws Exception;

    /**
     * 从 注册中心 移除一个服务信息
     *
     * @param serviceInfo 服务信息
     */
    void unregister(ServiceInfo serviceInfo) throws Exception;

    /**
     * 关闭与服务注册中心的连接
     */
    void destroy() throws Exception;
}
