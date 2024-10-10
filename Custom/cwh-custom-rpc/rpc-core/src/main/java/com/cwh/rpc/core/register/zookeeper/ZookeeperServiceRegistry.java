package com.cwh.rpc.core.register.zookeeper;

import com.cwh.rpc.core.common.ServiceInfo;
import com.cwh.rpc.core.exception.RpcException;
import com.cwh.rpc.core.register.ServiceRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 13:20
 * @Version 1.0
 * @ClassName ZookeeperServiceRegistry
 * @Description This is a general-purpose Java class.
 */
@Slf4j
public class ZookeeperServiceRegistry implements ServiceRegistry {


    private static final int SESSION_TIMEOUT = 60 * 1000;

    private static final int CONNECT_TIMEOUT = 15 * 1000;

    private static final int BASE_SLEEP_TIME = 3 * 1000;

    private static final int MAX_RETRY = 10;

    private static final String BASE_PATH = "/wxy_rpc";

    private CuratorFramework client;

    private ServiceDiscovery<ServiceInfo> serviceDiscovery;


    /**
     * 构造方法，传入 zk 的连接地址，如：127.0.0.1:2181
     *
     * @param registryAddress zookeeper 的连接地址
     */
    public ZookeeperServiceRegistry(String registryAddress) {
        try {
            // 创建zk客户端示例
            client = CuratorFrameworkFactory
                    .newClient(registryAddress, SESSION_TIMEOUT, CONNECT_TIMEOUT,
                            new ExponentialBackoffRetry(BASE_SLEEP_TIME, MAX_RETRY));
            // 开启客户端通信
            client.start();

            // 构建 ServiceDiscovery 服务注册中心
            serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceInfo.class)
                    .client(client)
                    .serializer(new JsonInstanceSerializer<>(ServiceInfo.class))
                    .basePath(BASE_PATH)
                    .build();

            serviceDiscovery.start();
        } catch (Exception e) {
            log.error("An error occurred while starting the zookeeper registry: ", e);
        }
    }

    @Override
    public void register(ServiceInfo serviceInfo) {
        try {
            ServiceInstance<ServiceInfo> serviceInstance = ServiceInstance.<ServiceInfo>builder()
                    .name(serviceInfo.getServiceName())
                    .address(serviceInfo.getAddress())
                    .port(serviceInfo.getPort())
                    .payload(serviceInfo)
                    .build();
            serviceDiscovery.registerService(serviceInstance);
            log.info("Successfully registered [{}] service.", serviceInstance.getName());
        } catch (Exception e) {
            throw new RpcException(String.format("An error occurred when rpc server registering [%s] service.",
                    serviceInfo.getServiceName()), e);
        }
    }

    @Override
    public void unregister(ServiceInfo serviceInfo) throws Exception {
        ServiceInstance<ServiceInfo> serviceInstance = ServiceInstance.<ServiceInfo>builder()
                .name(serviceInfo.getServiceName())
                .address(serviceInfo.getAddress())
                .port(serviceInfo.getPort())
                .payload(serviceInfo)
                .build();
        serviceDiscovery.unregisterService(serviceInstance);
        log.warn("Successfully unregistered {} service.", serviceInstance.getName());
    }

    @Override
    public void destroy() throws Exception {
        serviceDiscovery.close();
        client.close();
        log.info("Destroy zookeeper registry completed.");
    }
//    private CuratorFramework client;
//
//    private ServiceDiscovery<ServiceInfo> serviceDiscovery;
//
//    public ZookeeperServiceRegistry(){
//        /**
//         *
//         * @param connectString       连接字符串。zk server 地址和端口 "192.168.149.135:2181,192.168.149.136:2181"
//         * @param sessionTimeoutMs    会话超时时间 单位ms
//         * @param connectionTimeoutMs 连接超时时间 单位ms
//         * @param retryPolicy         重试策略
//         */
//        //重试策略
//        try {
//        RetryPolicy retryPolicy = new ExponentialBackoffRetry(ZookeeperConstant.BASE_SLEEP_TIME, ZookeeperConstant.MAX_RETRY);
//        //2.第二种方式
//        //CuratorFrameworkFactory.builder();
//        client = CuratorFrameworkFactory.builder()
//                .connectString(ZookeeperConstant.CONNECT_ADDRESS)
//                .sessionTimeoutMs(ZookeeperConstant.SESSION_TIMEOUT)
//                .connectionTimeoutMs(ZookeeperConstant.CONNECT_TIMEOUT)
//                .retryPolicy(retryPolicy)
//                .namespace(ZookeeperConstant.BASE_PATH)
//                .build();
//        client.start();
//
//        serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceInfo.class)
//                .client(client)
//                .serializer(new JsonInstanceSerializer<>(ServiceInfo.class))
//                .basePath(ZookeeperConstant.BASE_PATH)
//                .build();
//        //开启连接
//
//            serviceDiscovery.start();
//        } catch (Exception e) {
//            log.error("An error occurred while starting the zookeeper registry: ", e);
//        }
//
//
//    }
//
//    @Override
//    public void register(ServiceInfo serviceInfo) {
//        try {
//            ServiceInstance<ServiceInfo> serviceInstance = ServiceInstance.<ServiceInfo>builder()
//                    .name(serviceInfo.getServiceName())
//                    .address(serviceInfo.getAddress())
//                    .port(serviceInfo.getPort())
//                    .payload(serviceInfo)
//                    .build();
//            serviceDiscovery.registerService(serviceInstance);
//            log.info("Successfully registered [{}] service.", serviceInstance.getName());
//        } catch (Exception e) {
//            throw new RpcException(String.format("An error occurred when rpc server registering [%s] service.",
//                    serviceInfo.getServiceName()), e);
//        }
//    }
//
//    @Override
//    public void unregister(ServiceInfo serviceInfo) throws Exception {
//        ServiceInstance<ServiceInfo> serviceInstance = ServiceInstance.<ServiceInfo>builder()
//                .name(serviceInfo.getServiceName())
//                .address(serviceInfo.getAddress())
//                .port(serviceInfo.getPort())
//                .payload(serviceInfo)
//                .build();
//        serviceDiscovery.unregisterService(serviceInstance);
//        log.warn("Successfully unregistered {} service.", serviceInstance.getName());
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        serviceDiscovery.close();;
//        client.close();
//        log.info("Destroy zookeeper registry completed.");
//    }
}
