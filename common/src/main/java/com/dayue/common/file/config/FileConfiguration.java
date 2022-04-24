package com.dayue.common.file.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengdayue
 * @time 2022/4/24 22:30
 */
@ConfigurationProperties(prefix = "file.info")
@Configuration
public class FileConfiguration {

    /**
     * 文件大小
     */
    private Integer maxSize;

    /**
     * 文件前缀路径
     */
    private String pathPrefix;

    public Integer getMaxSize() {
        return maxSize;
    }

    public String getPathPrefix() {
        return pathPrefix;
    }
}
