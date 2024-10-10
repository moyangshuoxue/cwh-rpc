package com.cwh.rpc.core.extension;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 19:13
 * @Version 1.0
 * @ClassName ExtensionFactory
 * @Description Extension 工厂类
 */
@SPI
public interface ExtensionFactory {

    /**
     * 得到扩展对象实例
     *
     * @param type 对象类型
     * @param name 对象名称
     * @param <T>  实例类型
     * @return 返回对象实例
     */
    <T> T getExtension(Class<?> type, String name);

}
