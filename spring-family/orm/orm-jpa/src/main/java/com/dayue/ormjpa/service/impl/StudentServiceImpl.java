package com.dayue.ormjpa.service.impl;
import cn.hutool.json.JSONUtil;
import com.dayue.ormjpa.domain.Course;
import java.util.ArrayList;
import java.util.List;

import com.dayue.ormjpa.domain.Student;
import com.dayue.ormjpa.repository.CourseRepository;
import com.dayue.ormjpa.repository.StudentRepository;
import com.dayue.ormjpa.service.StudentService;
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
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public void save() {
        Student student = new Student();
        student.setName("李四");
        student.setAge(10);
        Course course = new Course();
        course.setName("数学课");
        Course saveCourse = courseRepository.save(course);
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(saveCourse);
        student.setCourses(courses);
        Student saveStudent = studentRepository.save(student);

        // 不开启事务会因为没Session而报LazyInitializationException
        List<Course> lisiCourses = saveStudent.getCourses();
        log.info("lisi:{}", JSONUtil.toJsonStr(saveStudent));
        if (lisiCourses != null && lisiCourses.size() != 0) {
            lisiCourses.forEach(item -> log.info("lisi课程：{}", JSONUtil.toJsonStr(item)));
        }
    }
}
