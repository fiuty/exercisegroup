package com.dayue.enable.typeone;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zhengdayue
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HelloWorldConfiguration.class)
public @interface EnableHelloWorld {
}
