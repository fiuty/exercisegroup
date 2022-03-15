package com.dayue.springfamily.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zhengdayue
 */
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;
}
