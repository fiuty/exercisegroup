package com.dayue.ormmybatis.mapper;

import com.dayue.ormmybatis.domain.StudentCourseRelation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @author zhengdayue
 * @time 2022/3/24 23:35
 */
@Mapper
public interface StudentCourseRelationMapper {

    @Insert("insert into student_course_relation (student_id, course_id) values (#{studentId},#{courseId})")
    @Options(useGeneratedKeys = true)
    int save(StudentCourseRelation studentCourseRelation);

}
