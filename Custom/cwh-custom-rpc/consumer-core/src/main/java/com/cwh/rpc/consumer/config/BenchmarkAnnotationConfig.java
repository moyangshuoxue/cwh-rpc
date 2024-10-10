package com.cwh.rpc.consumer.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/10 20:16
 * @Version 1.0
 * @ClassName BenchmarkAnnotationConfig
 * @Description 用于配置JMH对本RPC调用的性能基准测试  还可以用 @Benchmark标记要测试的方法
 */
@ComponentScan("com.cwh.rpc")
@Configuration
@PropertySource(value = "classpath:application.yml", factory = BenchmarkAnnotationConfig.YamlPropertySourceFactory.class)
public class BenchmarkAnnotationConfig {

    /**
     * 读取 yaml 配置文件的属性工厂类
     */
    static class YamlPropertySourceFactory implements PropertySourceFactory {

        @Override
        public org.springframework.core.env.PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
            Properties propertiesFromYaml = loadYamlIntoProperties(resource);
            String sourceName = name != null ? name : resource.getResource().getFilename();
            return new PropertiesPropertySource(Objects.requireNonNull(sourceName), propertiesFromYaml);
        }

        private Properties loadYamlIntoProperties(EncodedResource resource) throws FileNotFoundException {
            try {
                YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
                factory.setResources(resource.getResource());
                factory.afterPropertiesSet();
                return factory.getObject();
            } catch (IllegalStateException e) {
                // for ignoreResourceNotFound
                Throwable cause = e.getCause();
                if (cause   instanceof FileNotFoundException)
                    throw (FileNotFoundException) e.getCause();
                throw e;
            }
        }
    }
}

