package com.dayue.enable.typetwo.selector;

import com.dayue.enable.typetwo.annotations.EnableActionServer;
import com.dayue.enable.typetwo.enums.ActionType;
import com.dayue.enable.typetwo.service.StudentActionServerImpl;
import com.dayue.enable.typetwo.service.TeacherActionServerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengdayue
 */
@Slf4j
public class ServerImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //获取注解@EnableActionServer元信息
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableActionServer.class.getName());
        assert annotationAttributes != null;
        //通过获取的注解@EnableActionServer元信息，取定义好的方法属性，并把对象Object强制转换为ActionType枚举类型
        ActionType serverType = (ActionType) annotationAttributes.get("serverType");
        log.info("serverType：{}，getServerType(serverType):{}", serverType, getServerType(serverType));
        //最后返回要选择的类名，注册为spring bean组件
        return new String[]{getServerType(serverType)};
    }

    //封装一层，通过枚举值返回不同的类名
    public String getServerType(ActionType serverType) {
        Map<ActionType, String> serverClassMap; serverClassMap = new HashMap<>(2);
        serverClassMap.put(ActionType.TEACHER, TeacherActionServerImpl.class.getName());
        serverClassMap.put(ActionType.STUDENT, StudentActionServerImpl.class.getName());
        return serverClassMap.get(serverType);
    }

}
