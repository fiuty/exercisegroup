package com.dayue.ormjpa.repository;

import com.dayue.ormjpa.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhengdayue
 * @time 2022/3/24 23:11
 */
public interface CourseRepository extends JpaSpecificationExecutor<Course>, JpaRepository<Course, Long> {
}
