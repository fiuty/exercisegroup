package com.dayue.enable.typetwo.annotations;

import com.dayue.enable.typetwo.enums.ActionType;
import com.dayue.enable.typetwo.registrar.ServerImportBeanDefinitionRegistrar;
import com.dayue.enable.typetwo.selector.ServerImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zhengdayue
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(ServerImportSelector.class)
@Import(ServerImportBeanDefinitionRegistrar.class)
public @interface EnableActionServer {

    /**
     * 设置动作类型，默认为老师上课
     */
    ActionType serverType() default ActionType.TEACHER;
}
