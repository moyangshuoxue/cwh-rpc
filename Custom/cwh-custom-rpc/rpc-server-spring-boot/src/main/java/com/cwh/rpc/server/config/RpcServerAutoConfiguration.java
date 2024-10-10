package com.cwh.rpc.server.config;

import com.cwh.rpc.core.register.ServiceRegistry;
import com.cwh.rpc.core.register.nacos.NacosServiceRegistry;
import com.cwh.rpc.core.register.zookeeper.ZookeeperServiceRegistry;
import com.cwh.rpc.server.spring.RpcServerBeanPostProcessor;
import com.cwh.rpc.server.transport.RpcServer;
import com.cwh.rpc.server.transport.http.HttpRpcServer;
import com.cwh.rpc.server.transport.netty.NettyRpcServer;
import com.cwh.rpc.server.transport.socket.SocketRpcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.imageio.spi.ServiceRegistry;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 14:31
 * @Version 1.0
 * @ClassName RpcServerAutoConfiguration
 * @Description  RpcServer 端的自动配置类
 */
@Configuration
@EnableConfigurationProperties(RpcServerProperties.class)
public class RpcServerAutoConfiguration {

    @Autowired
    RpcServerProperties properties;

    /**
     * 创建 ServiceRegistry 实例 bean，当没有配置时默认使用 zookeeper 作为配置中心
     */
    @Bean(name = "serviceRegistry")
    @Primary
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "rpc.server", name = "registry", havingValue = "zookeeper", matchIfMissing = true)
    public ServiceRegistry zookeeperServiceRegistry() {
        return new ZookeeperServiceRegistry(properties.getRegistryAddr());
    }

    @Bean(name = "serviceRegistry")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "rpc.server", name = "registry", havingValue = "nacos")
    public ServiceRegistry nacosServiceRegistry() {
        return new NacosServiceRegistry(properties.getRegistryAddr());
    }

    // 当没有配置通信协议属性时，默认使用 netty 作为通讯协议
    @Bean(name = "rpcServer")
    @Primary
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "rpc.server", name = "transport", havingValue = "netty", matchIfMissing = true)
    public RpcServer nettyRpcServer() {
        return new NettyRpcServer();
    }

    @Bean(name = "rpcServer")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "rpc.server", name = "transport", havingValue = "http")
    @ConditionalOnClass(name = {"org.apache.catalina.startup.Tomcat"})
    public RpcServer httpRpcServer() {
        return new HttpRpcServer();
    }

    @Bean(name = "rpcServer")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "rpc.server", name = "transport", havingValue = "socket")
    public RpcServer socketRpcServer() {
        return new SocketRpcServer();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean({ServiceRegistry.class, RpcServer.class})
    public RpcServerBeanPostProcessor rpcServerBeanPostProcessor(@Autowired ServiceRegistry serviceRegistry,
                                                                 @Autowired RpcServer rpcServer,
                                                                 @Autowired RpcServerProperties properties) {

        return new RpcServerBeanPostProcessor(serviceRegistry, rpcServer, properties);
    }

}
