package com.cwh.rpc.core.extension.factory;

import com.cwh.rpc.core.extension.ExtensionFactory;
import com.cwh.rpc.core.extension.ExtensionLoader;
import com.cwh.rpc.core.extension.SPI;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 19:16
 * @Version 1.0
 * @ClassName SpiExtensionFactory
 * @Description This is a general-purpose Java class.
 */
public class SpiExtensionFactory implements ExtensionFactory {
    @Override
    public <T> T getExtension(Class<?> type, String name) {
        if (type.isInterface() && type.isAnnotationPresent(SPI.class)) {
            ExtensionLoader<?> extensionLoader = ExtensionLoader.getExtensionLoader(type);
            // todo: implement this method
        }
        return null;
    }
}
