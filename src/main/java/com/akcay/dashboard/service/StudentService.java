package com.akcay.dashboard.service;

import com.akcay.dashboard.entity.Student;
import com.akcay.dashboard.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Page<Student> getStudentsByCourse(Long courseId, Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            return studentRepository.findByCoursesIdAndNameContainingIgnoreCase(courseId, search, pageable);
        } else {
            return studentRepository.findByCoursesId(courseId, pageable);
        }
    }
}
