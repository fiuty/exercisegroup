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
@ToString(exclude = "students")
@EqualsAndHashCode(exclude = "students", callSuper = false)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "courses")
    private List<Student> students = new ArrayList<>();
}
