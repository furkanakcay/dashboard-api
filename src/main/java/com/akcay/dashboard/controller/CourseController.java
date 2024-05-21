package com.akcay.dashboard.controller;

import com.akcay.dashboard.adapter.CourseAdapter;
import com.akcay.dashboard.adapter.StudentAdapter;
import com.akcay.dashboard.constants.ControllerConstants;
import com.akcay.dashboard.dto.CourseDTO;
import com.akcay.dashboard.dto.StudentDTO;
import com.akcay.dashboard.entity.Course;
import com.akcay.dashboard.entity.Student;
import com.akcay.dashboard.exception.ResourceNotFoundException;
import com.akcay.dashboard.repository.CourseRepository;
import com.akcay.dashboard.repository.StudentRepository;
import com.akcay.dashboard.service.CourseService;
import com.akcay.dashboard.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(ControllerConstants.API_PREFIX + "/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseAdapter courseAdapter;

    @Autowired
    private StudentAdapter studentAdapter;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@Validated @RequestBody CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        Course savedCourse = courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseAdapter.toDto(savedCourse));
    }

    @PostMapping("/{id}/students")
    public ResponseEntity<StudentDTO> createStudent(@PathVariable Long id, @Validated @RequestBody StudentDTO studentDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
        Student student = studentAdapter.fromDto(studentDTO);
        student.setCourses(Set.of(course));
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentAdapter.toDto(savedStudent));
    }

    @GetMapping
    public ResponseEntity<Page<CourseDTO>> getAllCourses(Pageable pageable,
                                                         @RequestParam(required = false) String searchText) {
        Page<Course> courses = courseService.getAllDepartments(pageable, searchText);
        return ResponseEntity.ok(courses.map(courseAdapter::toDto));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Page<StudentDTO>> getStudentsByCourseId(@PathVariable Long id,
                                                                  Pageable pageable,
                                                                  @RequestParam(required = false) String searchText) {
        Page<Student> students = studentService.getStudentsByCourse(id, pageable, searchText);
        return ResponseEntity.ok(students.map(studentAdapter::toDto));
    }
}
