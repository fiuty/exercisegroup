package com.dayue.springfamily.service.impl;


import com.dayue.springfamily.service.DefineJcbcTemplate;
import com.dayue.springfamily.service.DefineRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
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
    public <T> T queryForObject(String sql, DefineRowMapper<T> defineRowMapper, DataSource dataSource) {
        //一部分是准备和释放资源以及执行 SQL 语句，另一部分则是处理 SQL 执行结果
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //创建dataSource,获取连接
            connection = dataSource.getConnection();
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
