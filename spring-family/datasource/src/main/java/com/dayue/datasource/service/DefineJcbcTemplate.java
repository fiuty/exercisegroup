package com.dayue.datasource.service;


import javax.sql.DataSource;

/**
 * @author zhengdayue
 */
public interface DefineJcbcTemplate {

    <T> T queryForObject(String sql, DefineRowMapper<T> defineRowMapper, DataSource dataSource);

}
