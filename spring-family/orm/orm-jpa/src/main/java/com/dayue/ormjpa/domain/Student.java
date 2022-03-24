package com.dayue.ormjpa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengdayue
 * @time 2022/3/24 18:43
 */
@Data
@Table
@Entity
@ToString(exclude = "courses")
@EqualsAndHashCode(exclude = "courses", callSuper = false)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    // 在保存course、student对象的时候，对student对象的courses赋值，hibernate自动对中间表student_course_relation数据插入
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(
            name = "student_course_relation",
            joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")})
    private List<Course> courses = new ArrayList<>();

}
