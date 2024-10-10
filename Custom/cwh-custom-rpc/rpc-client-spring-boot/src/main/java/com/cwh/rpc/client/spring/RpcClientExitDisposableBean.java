package com.cwh.rpc.client.spring;

import com.cwh.rpc.core.discovery.ServiceDiscovery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 11:12
 * @Version 1.0
 * @ClassName RpcClientExitDisposableBean
 * @Description  客户端退出时执行额外操作类
 */
@Slf4j
public class RpcClientExitDisposableBean implements DisposableBean {

    /**
     * 服务发现中心
     */
    private final ServiceDiscovery serviceDiscovery;

    public RpcClientExitDisposableBean(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    /**
     * 客户端退出时执行的一些额外操作（关闭资源、连接等）
     *
     * @throws Exception 异常
     */
    @Override
    public void destroy() throws Exception {
        try {
            if (serviceDiscovery != null) {
                serviceDiscovery.destroy();
            }
            log.info("Rpc client resource release completed and exited successfully.");
        } catch (Exception e) {
            log.warn("An exception occurred while executing the destroy operation when the rpc client exited, {}.",
                    e.getMessage());
        }
    }
}