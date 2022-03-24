package com.dayue.ormmybatis.mapper;

import com.dayue.ormmybatis.domain.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @author zhengdayue
 * @time 2022/3/24 23:26
 */
@Mapper
public interface CourseMapper {

    @Insert("insert into course(name) values (#{name})")
    @Options(useGeneratedKeys = true)
    int save(Course course);

}
