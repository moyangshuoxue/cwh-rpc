package com.cwh.rpc.core.extension;

import java.lang.annotation.*;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 9:33
 * @Version 1.0
 * @ClassName SPI
 * @Description SPI 注解，被标注的类表示为需要加载的扩展类接口
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SPI {

}