package com.akcay.dashboard.repository;

import com.akcay.dashboard.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findByCoursesId(Long courseId, Pageable pageable);
    Page<Student> findByCoursesIdAndNameContainingIgnoreCase(Long courseId, String name, Pageable pageable);

}
