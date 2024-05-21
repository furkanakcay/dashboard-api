package com.akcay.dashboard.service;

import com.akcay.dashboard.entity.Course;
import com.akcay.dashboard.entity.Department;
import com.akcay.dashboard.repository.CourseRepository;
import com.akcay.dashboard.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Page<Course> getAllDepartments(Pageable pageable, String searchText) {
        if (searchText != null && !searchText.isEmpty()) {
            return courseRepository.findByNameContainingIgnoreCase(searchText, pageable);
        } else {
            return courseRepository.findAll(pageable);
        }
    }
}
