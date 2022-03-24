package com.dayue.datasource.service;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhengdayue
 */
@FunctionalInterface
public interface DefineRowMapper<T>{

    T mapRow(ResultSet rs) throws SQLException;
}
