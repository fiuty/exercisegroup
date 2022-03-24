package com.dayue.ormjpa.repository;

import com.dayue.ormjpa.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhengdayue
 * @time 2022/3/24 18:55
 */
public interface StudentRepository extends JpaSpecificationExecutor<Student>, JpaRepository<Student, Long> {

    Student findByName(String name);
}
