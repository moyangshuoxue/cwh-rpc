package com.cwh.rpc.server.annotation;

import java.lang.annotation.*;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 14:04
 * @Version 1.0
 * @ClassName RpcService
 * @Description This is a general-purpose Java class.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RpcService {

    /**
     * 对外暴露服务的接口类型，默认为 void.class
     */
    Class<?> interfaceClass() default void.class;

    /**
     * 对外暴露服务的接口名（全限定名），默认为 ""
     */
    String interfaceName() default "";

    /**
     * 版本号，默认 1.0
     */
    String version() default "1.0";

}
