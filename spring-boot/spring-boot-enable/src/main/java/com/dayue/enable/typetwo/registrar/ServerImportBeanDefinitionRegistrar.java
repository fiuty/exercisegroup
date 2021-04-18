package com.dayue.enable.typetwo.registrar;

import com.dayue.enable.typetwo.selector.ServerImportSelector;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.stream.Stream;

/**
 * @author zhengdayue
 */
public class ServerImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //复用前面实现好的ImportSelector
        ImportSelector importSelector = new ServerImportSelector();
        //筛选ClassNames集合
        String[] selectClassNames = importSelector.selectImports(importingClassMetadata);
        Stream.of(selectClassNames)
                //转化为 BeanDefinitionBuilder 对象
                .map(BeanDefinitionBuilder::genericBeanDefinition)
                //转化为 BeanDefinition 对象
                .map(BeanDefinitionBuilder::getBeanDefinition)
                //注册 BeanDefinition 到 BeanDefinitionRegistry
                .forEach(beanDefinition -> BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry));
    }
}
