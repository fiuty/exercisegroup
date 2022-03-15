package com.dayue.springfamily.service.impl;

import cn.hutool.json.JSONUtil;
import com.dayue.springfamily.domain.Student;
import com.dayue.springfamily.service.DefineJcbcTemplate;
import com.dayue.springfamily.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhengdayue
 * @time 2022/3/13 7:51
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    @Qualifier("oneDataSource")
    private DataSource oneDataSource;

    @Autowired
    @Qualifier("twoDataSource")
    private DataSource twoDataSource;

    @Autowired
    private DefineJcbcTemplate defineJcbcTemplate;


    @Override
    @Transactional(transactionManager = "oneDatasourceManager")
    public void dateSourceOne() {
        Student student1 = defineJcbcTemplate.queryForObject("select * from student where id = 1", this::defineRowMapper, oneDataSource);
        log.info("studen1:{}", JSONUtil.toJsonStr(student1));
    }

    @Override
    @Transactional(transactionManager = "twoDatasourceManager")
    public void dateSourceTwo() {
        Student student2 = defineJcbcTemplate.queryForObject("select * from student where id = 1", this::defineRowMapper, twoDataSource);
        log.info("studen2:{}", JSONUtil.toJsonStr(student2));
    }

    private Student defineRowMapper(ResultSet resultSet) {
        try {
            //ResultSet是一个结果集，想读出来，必须要next方法才行
            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                return student;
            }
        } catch (SQLException e) {
            log.error("e:", e);
        }
        return null;
    }
}
