package com.dayue.ormmybatis.domain;

import lombok.Data;

/**
 * @author zhengdayue
 * @time 2022/3/24 23:34
 */
@Data
public class StudentCourseRelation {

    private Long id;

    private Long studentId;

    private Long courseId;

}
