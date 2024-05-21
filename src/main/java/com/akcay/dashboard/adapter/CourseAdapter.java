package com.akcay.dashboard.adapter;

import com.akcay.dashboard.dto.CourseDTO;
import com.akcay.dashboard.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseAdapter {

    public CourseDTO toDto(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        return dto;
    }
}
