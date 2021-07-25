package com.dayue.spring.pattern.templates.jdbctemplate.service.impl;

import cn.hutool.json.JSONUtil;
import com.dayue.spring.pattern.templates.jdbctemplate.domain.Student;
import com.dayue.spring.pattern.templates.jdbctemplate.service.DefineJcbcTemplate;
import com.dayue.spring.pattern.templates.jdbctemplate.service.DefineRowMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhengdayue
 */
@Service
@Slf4j
public class DefineJcbcTemplateImpl implements DefineJcbcTemplate {

    @Override
    public <T> T queryForObject(String sql, DefineRowMapper<T> defineRowMapper) {
        //一部分是准备和释放资源以及执行 SQL 语句，另一部分则是处理 SQL 执行结果
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //创建dataSource
            ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("123456");
            comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/exercisegroup?useUnicode=true&characterEncoding=utf-8");
            //获取连接
            connection = comboPooledDataSource.getConnection();
            //执行查询
            preparedStatement = connection.prepareStatement(sql);
            //获取执行结果
            resultSet = preparedStatement.executeQuery();
            return defineRowMapper.mapRow(resultSet);
        } catch (Exception e) {
            log.error("e:", e);
        } finally {
            //关闭资源
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    log.error("e:", e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    log.error("e:", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("e:", e);
                }
            }
        }
        return null;
    }
}
