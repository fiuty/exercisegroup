package com.dayue.project.service;


import com.dayue.demo1.Student;
import com.dayue.demo2.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Fiuty
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private Student student;

    @Autowired
    private Teacher teacher;

    @Override
    public String studentInfo() {
        return student.getName() + ":" + student.getAge();
    }

    @Override
    public String teacherInfo() {
        return teacher.getName() + ":" + teacher.getAge();
    }
}
