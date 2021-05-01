package com.dayue.springbootconditional.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import java.util.Map;

/**
 * @author zhengdayue
 */
public class OnSystemPropertyCondition implements Condition {

    @Value("${language}")
    private String language;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //获取ConditionalOnSystemProperty所有的属性方法值
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnSystemProperty.class.getName());
        assert annotationAttributes != null;
        //获取ConditionalOnSystemProperty#language()方法值
        String annotationLanguage = (String) annotationAttributes.get("language");
        //配置文件动态选择bean
        if (language.equals(annotationLanguage)) {
            return true;
        }
        return false;
    }
}
