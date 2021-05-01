package com.dayue.springbootconditional.configuration;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author zhengdayue
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
@Documented
@Conditional(OnSystemPropertyCondition.class)
public @interface ConditionalOnSystemProperty {

    /**
     * 语言选择
     */
    String language();
}
