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
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableActionServer.class.getName());
        assert annotationAttributes != null;
        ActionType serverType = (ActionType) annotationAttributes.get("serverType");
        log.info("getServerType(serverType):{}", getServerType(serverType));
        return new String[]{getServerType(serverType)};
    }


    public String getServerType(ActionType serverType) {
        Map<ActionType, String> serverClassMap; serverClassMap = new HashMap<>(2);
        serverClassMap.put(ActionType.TEACHER, TeacherActionServerImpl.class.getName());
        serverClassMap.put(ActionType.STUDENT, StudentActionServerImpl.class.getName());
        return serverClassMap.get(serverType);
    }

}
