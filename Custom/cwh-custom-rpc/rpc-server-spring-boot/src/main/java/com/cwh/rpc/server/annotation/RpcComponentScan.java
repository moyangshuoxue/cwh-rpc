package com.cwh.rpc.server.annotation;

import com.cwh.rpc.server.spring.RpcBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 14:06
 * @Version 1.0
 * @ClassName RpcComponentScan
 * @Description This is a general-purpose Java class.
 *   自定义 Rpc 组件扫描注解
 *   <p>
 *   {@link RpcComponentScan} 类上用 @{@link Import} 引入了 {@link RpcBeanDefinitionRegistrar} 类，而这个类是一个 {@link ImportBeanDefinitionRegistrar} 的实现类，
 *   Spring 容器在解析该类型的 Bean 时会调用其 {@link ImportBeanDefinitionRegistrar#registerBeanDefinitions(AnnotationMetadata, BeanDefinitionRegistry)} 方法，
 *   将 @{@link RpcComponentScan} 注解上的信息提取成 {@link AnnotationMetadata} 以及容器注册器对象作为此方法的参数，这个就是自定义注解式组件扫描的关键逻辑。
 *   </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(RpcBeanDefinitionRegistrar.class)
public @interface RpcComponentScan {

    /**
     * 扫描包路径
     */
    @AliasFor("basePackages")
    String[] value() default {};

    /**
     * 扫描包路径
     */
    @AliasFor("value")
    String[] basePackages() default {};

}
