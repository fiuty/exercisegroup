package com.dayue.spring.pattern.templates.jdbctemplate.service;


/**
 * @author zhengdayue
 */
public interface DefineJcbcTemplate {

    <T> T queryForObject(String sql, DefineRowMapper<T> defineRowMapper);

}
