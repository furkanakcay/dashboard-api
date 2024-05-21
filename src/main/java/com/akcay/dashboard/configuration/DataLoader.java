package com.akcay.dashboard.configuration;

import com.akcay.dashboard.entity.Course;
import com.akcay.dashboard.entity.Department;
import com.akcay.dashboard.entity.Employee;
import com.akcay.dashboard.entity.Student;
import com.akcay.dashboard.repository.CourseRepository;
import com.akcay.dashboard.repository.DepartmentRepository;
import com.akcay.dashboard.repository.EmployeeRepository;
import com.akcay.dashboard.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create Departments
        Department department1 = new Department(null, "Human Resources", null);
        Department department2 = new Department(null, "Engineering", null);
        Department department3 = new Department(null, "Finance", null);
        Department department4 = new Department(null, "Admin", null);
        Department department5 = new Department(null, "Sales", null);
        Department department6 = new Department(null, "Store", null);
        Department department7 = new Department(null, "E-commerce", null);
        Department department8 = new Department(null, "Security", null);
        departmentRepository.saveAll(List.of(department1,department2,department3,department4,
                department5,department6,department7,department8));

        //create employees under department 1
        List<Employee> employees = new ArrayList<>();
        for(int i = 0; i < 25; i++) {
            employees.add(new Employee(null, String.format("Dept1 - Employee %d", i), String.format("employee%d@email.com", i), i%2 == 0 ? "Male" : "Female", 100000 + i*10000, "Human Resources Specialist",department1));
        }
        for(int i = 25; i < 50; i++) {
            employees.add(new Employee(null, String.format("Dept1 - Employee %d", i), String.format("employee%d@email.com", i), i%2 == 0 ? "Male" : "Female", 100000 + i*15000, "Head Hunter",department1));
        }
        employeeRepository.saveAll(employees);

        //create employees under department 1
        employees = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            employees.add(new Employee(null, String.format("Dept2 - Employee %d", i), String.format("employee%d@email.com", i), i%2 == 0 ? "Male" : "Female", 200000 + i*20000, "Software Engineer",department2));
        }
        employeeRepository.saveAll(employees);
        // Create Courses
        Course course1 = new Course(null, "Spring Boot", null);
        Course course2 = new Course(null, "React", null);
        Course course3 = new Course(null, "Full Stack Development", null);
        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);

        // Create Students
        List<Student> students = List.of(new Student(null, "Student1", "student1@email.com", "Male", "1st class", new HashSet<>(List.of(course1, course2))),
                                            new Student(null, "Student2", "student2@email.com", "Female", "2st class", new HashSet<>(List.of(course1))),
                                            new Student(null, "Student3", "student3@email.com", "Male", "1st class", new HashSet<>(List.of(course3))),
                                            new Student(null, "Student4", "student4@email.com", "Male", "4st class", new HashSet<>(List.of(course1, course2, course3))));
        studentRepository.saveAll(students);
    }
}
