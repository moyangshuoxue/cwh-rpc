package com.cwh.rpc.core.register.nacos;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.cwh.rpc.core.common.ServiceInfo;
import com.cwh.rpc.core.exception.RpcException;
import com.cwh.rpc.core.register.ServiceRegistry;
import com.cwh.rpc.core.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 14:14
 * @Version 1.0
 * @ClassName NacosServiceRegistry
 * @Description Nacos 实现服务注册中心类
 */
@Slf4j
public class NacosServiceRegistry implements ServiceRegistry {

    /**
     * Nacos 服务发现
     */
    private NamingService namingService;


    public NacosServiceRegistry(String registryAddr) {
        try {
            // 创建Nacos命名服务
            namingService = NamingFactory.createNamingService(registryAddr);

        } catch (Exception e) {
            log.error("An error occurred while starting the nacos registry: ", e);
        }
    }

    @Override
    public void register(ServiceInfo serviceInfo) throws Exception {
        try {
            // 创建服务实例
            Instance instance = new Instance();
            instance.setServiceName(serviceInfo.getServiceName());
            instance.setIp(serviceInfo.getAddress());
            instance.setPort(serviceInfo.getPort());
            instance.setHealthy(true); // 服务是否健康，和服务发现有关，默认为 true
            instance.setMetadata(ServiceUtil.toMap(serviceInfo));

            // 注册实例
            namingService.registerInstance(instance.getServiceName(), instance);

            log.info("Successfully registered [{}] service.", instance.getServiceName());
        } catch (Exception e) {
            throw new RpcException(String.format("An error occurred when rpc server registering [%s] service.",
                    serviceInfo.getServiceName()), e);
        }
    }

    @Override
    public void unregister(ServiceInfo serviceInfo) throws Exception {
        try {
            // 创建服务实例
            Instance instance = new Instance();
            instance.setServiceName(serviceInfo.getServiceName());
            instance.setIp(serviceInfo.getAddress());
            instance.setPort(serviceInfo.getPort());
            instance.setHealthy(true); // 服务是否健康，和服务发现有关，默认为 true
            instance.setMetadata(ServiceUtil.toMap(serviceInfo));

            namingService.deregisterInstance(instance.getServiceName(), instance);
            log.warn("Successfully unregistered {} service.", instance.getServiceName());
        } catch (NacosException e) {
            throw new RpcException(e);
        }
    }

    @Override
    public void destroy() throws Exception {
        namingService.shutDown();
        log.info("Destroy nacos registry completed.");
    }
}
