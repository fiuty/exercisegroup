package com.dayue.spring.pattern.templates.jdbctemplate.service.impl;

import cn.hutool.json.JSONUtil;
import com.dayue.spring.pattern.templates.jdbctemplate.domain.Student;
import com.dayue.spring.pattern.templates.jdbctemplate.service.DefineJcbcTemplate;
import com.dayue.spring.pattern.templates.jdbctemplate.service.JdbcService;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class JdbcServiceImpl implements JdbcService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DefineJcbcTemplate defineJcbcTemplate;

    @Override
    public void originalJdbc() {
        //一部分是准备和释放资源以及执行 SQL 语句，另一部分则是处理 SQL 执行结果
        try {
            //创建dataSource
            ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("123456");
            comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/exercisegroup?useUnicode=true&characterEncoding=utf-8");
            //获取连接
            Connection connection = comboPooledDataSource.getConnection();
            //执行查询
            PreparedStatement preparedStatement = connection.prepareStatement("select * from student where id = 1");
            //获取执行结果
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                log.info("student:{}", JSONUtil.toJsonStr(student));
            }
            //关闭资源
            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (Exception e) {
            log.error("e:", e);
        }
    }

    @Override
    public void jdcbTemplate() {
        //省去建立数据库连接与连接释放资源
        Student student = jdbcTemplate.queryForObject("select * from student where id = 1", this::rowMapper);
        log.info("student:{}", JSONUtil.toJsonStr(student));
    }

    private Student rowMapper(ResultSet resultSet, int rowNum) {
        //之所以可以直接操作resultSet结果集，jdbcTemplate的RowMapperResultSetExtractor的extractData已经next()取出结果了
        Student student = new Student();
        try {
            student.setId(resultSet.getLong("id"));
            student.setName(resultSet.getString("name"));
            student.setAge(resultSet.getInt("age"));
            return student;
        } catch (SQLException e) {
            log.error("e:", e);
        }
        return null;
    }

    @Override
    public void defineJcbcTemplate() {
        defineJcbcTemplate.queryForObject("select * from student where id = 1", this::defineRowMapper);
    }

    private Student defineRowMapper(ResultSet resultSet) {
        try {
            //ResultSet是一个结果集，想读出来，必须要next方法才行
            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                log.info("student:{}", JSONUtil.toJsonStr(student));
                return student;
            }
        } catch (SQLException e) {
            log.error("e:", e);
        }
        return null;
    }


}
