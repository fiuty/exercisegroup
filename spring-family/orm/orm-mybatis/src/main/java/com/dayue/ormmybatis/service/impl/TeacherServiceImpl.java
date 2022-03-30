package com.dayue.ormmybatis.service.impl;

import cn.hutool.json.JSONUtil;
import com.dayue.ormmybatis.generator.mapper.TeacherMapper;
import com.dayue.ormmybatis.generator.model.Teacher;
import com.dayue.ormmybatis.generator.model.TeacherExample;
import com.dayue.ormmybatis.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhengdayue
 * @time 2022/3/30 23:55
 */
@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void doSomething() {
        Teacher teacher = new Teacher();
        teacher.withName("王五").withAge(20);
        teacherMapper.insert(teacher);

        /*
         复杂查询
         */
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andNameEqualTo("王五");
        List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
        if (teachers != null && teachers.size() != 0) {
            log.info("teacher:{}", JSONUtil.toJsonStr(teachers.get(0)));
        }

        /*
              分页查询
              @Select("select * from teacher order by id")
               List<Teacher> findAllWithRowBounds(RowBounds rowBounds);

               @Select("select * from teacher order by id")
               List<Teacher> findAllByPageParam(@Param("pageNum") int pageNum,
                                             @Param("pageSize") int pageSize);
         */
        List<Teacher> allByPageParam = teacherMapper.findAllByPageParam(0, 1);
        if (allByPageParam != null && allByPageParam.size() != 0) {
            log.info("teacher:{}", JSONUtil.toJsonStr(allByPageParam.get(0)));
        }

        List<Teacher> allWithRowBounds = teacherMapper.findAllWithRowBounds(new RowBounds(0, 1));
        if (allWithRowBounds != null && allWithRowBounds.size() != 0) {
            log.info("teacher:{}", JSONUtil.toJsonStr(allWithRowBounds.get(0)));
        }
    }
}
