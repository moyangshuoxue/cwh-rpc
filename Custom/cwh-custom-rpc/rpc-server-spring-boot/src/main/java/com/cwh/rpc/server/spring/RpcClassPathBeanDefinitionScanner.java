package com.cwh.rpc.server.spring;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;

/**
 * @Author 蔡文瀚
 * @Date 2024/6/9 14:18
 * @Version 1.0
 * @ClassName RpcClassPathBeanDefinitionScanner
 * @Description 类路径下的包扫描器
 */
public class RpcClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    private Class<? extends Annotation> annotationType;

    public RpcClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry);
        registerFilters();
    }

    public RpcClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, Class<? extends Annotation> annotationType) {
        super(registry);
        this.annotationType = annotationType;
        // 放行指定的注解类型
        registerFilters();
    }

    /**
     * 注册过滤器，用来指定放行哪些类型
     */
    private void registerFilters() {
        // 放行指定 annotation 类型
        if (annotationType != null) {

            this.addIncludeFilter(new AnnotationTypeFilter(this.annotationType));
        } else { // 放行所有类型
            this.addIncludeFilter((metadataReader, metadataReaderFactory) -> true);
        }
    }

    @Override
    public int scan(String... basePackages) {
        return super.scan(basePackages);
    }
}

