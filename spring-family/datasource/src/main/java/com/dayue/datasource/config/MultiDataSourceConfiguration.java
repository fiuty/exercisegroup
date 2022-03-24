package com.dayue.datasource.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 多数据源配置，需要配置好数据库连接池DataSource，事务管理器PlatformTransactionManager
 *
 * @author zhengdayue
 * @time 2022/3/13 7:03
 */
@Configuration
@Slf4j
public class MultiDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("multi.datasource.one")
    public DataSourceProperties oneDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource oneDataSource() {
        DataSourceProperties dataSourceProperties = oneDataSourceProperties();
        log.info("one datasource url:{}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    /**
     * 事务管理器1
     * 这里@Resource注解的作用是以方法入参的参数名，选择从spring容器中的bean进行注入
     * 结合@Transactional注解来选择事务管理器1和事务管理器2，管理不同的数据源，数据库连接池
     */
    @Bean
    @Resource
    public PlatformTransactionManager oneDatasourceManager(DataSource oneDataSource) {
        return new DataSourceTransactionManager(oneDataSource);
    }


    @Bean
    @ConfigurationProperties("multi.datasource.two")
    public DataSourceProperties twoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource twoDataSource() {
        DataSourceProperties dataSourceProperties = twoDataSourceProperties();
        log.info("one datasource url:{}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    /**
     * 事务管理器2
     */
    @Bean
    @Resource
    public PlatformTransactionManager twoDatasourceManager(DataSource twoDataSource) {
        return new DataSourceTransactionManager(twoDataSource);
    }


}
