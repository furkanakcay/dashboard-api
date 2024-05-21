package com.akcay.dashboard.adapter;

import com.akcay.dashboard.dto.StudentDTO;
import com.akcay.dashboard.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentAdapter {

    public StudentDTO toDto(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setGrade(student.getGrade());
        dto.setGender(student.getGender());
        return dto;
    }

    public Student fromDto(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setGrade(dto.getGrade());
        student.setGender(dto.getGender());
        return student;
    }
}
