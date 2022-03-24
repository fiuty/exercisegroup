package com.dayue.ormmybatis.service.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.dayue.ormmybatis.domain.Course;
import com.dayue.ormmybatis.domain.Student;
import com.dayue.ormmybatis.domain.StudentCourseRelation;
import com.dayue.ormmybatis.mapper.CourseMapper;
import com.dayue.ormmybatis.mapper.StudentCourseRelationMapper;
import com.dayue.ormmybatis.mapper.StudentMapper;
import com.dayue.ormmybatis.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhengdayue
 * @time 2022/3/24 18:56
 */
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentCourseRelationMapper studentCourseRelationMapper;

    @Override
    @Transactional
    public void save() {
        Student student = new Student();
        student.setName("李四");
        student.setAge(10);
        Course course = new Course();
        course.setName("数学课");
        courseMapper.save(course);
        studentMapper.save(student);
        StudentCourseRelation studentCourseRelation = new StudentCourseRelation();
        studentCourseRelation.setStudentId(student.getId());
        studentCourseRelation.setCourseId(course.getId());
        studentCourseRelationMapper.save(studentCourseRelation);
        log.info("学生：{}，课程：{}，关系：{}", JSONUtil.toJsonStr(student), JSONUtil.toJsonStr(course),
                JSONUtil.toJsonStr(studentCourseRelation));
    }
}
