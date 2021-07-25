package com.dayue.spring.pattern.templates.jdbctemplate.service;


/**
 * @author zhengdayue
 */
public interface JdbcService {

    /**
     * 原生jdbc连接MySQL
     */
    void originalJdbc();

    /**
     * 使用spring jdbcTemplate连接MySQL
     */
    void jdcbTemplate();

    /**
     * 自定义jdbcTemplate连接MySQL
     */
    void defineJcbcTemplate();
}
