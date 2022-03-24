package com.dayue.ormmybatis.mapper;

import com.dayue.ormmybatis.domain.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @author zhengdayue
 * @time 2022/3/24 23:25
 */
@Mapper
public interface StudentMapper {

    @Insert("insert into student(name, age) values(#{name},#{age})")
    @Options(useGeneratedKeys = true)
    int save(Student student);
}
