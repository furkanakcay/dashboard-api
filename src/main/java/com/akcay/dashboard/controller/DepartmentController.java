package com.akcay.dashboard.controller;

import com.akcay.dashboard.adapter.DepartmentAdapter;
import com.akcay.dashboard.adapter.EmployeeAdapter;
import com.akcay.dashboard.constants.ControllerConstants;
import com.akcay.dashboard.dto.DepartmentDTO;
import com.akcay.dashboard.dto.EmployeeDTO;
import com.akcay.dashboard.entity.Department;
import com.akcay.dashboard.entity.Employee;
import com.akcay.dashboard.exception.ResourceNotFoundException;
import com.akcay.dashboard.repository.DepartmentRepository;
import com.akcay.dashboard.repository.EmployeeRepository;
import com.akcay.dashboard.service.DepartmentService;
import com.akcay.dashboard.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerConstants.API_PREFIX + "/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentAdapter departmentAdapter;

    @Autowired
    private EmployeeAdapter employeeAdapter;

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setName(departmentDTO.getName());
        Department savedDepartment = departmentRepository.save(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentAdapter.toDto(savedDepartment));
    }

    @PostMapping("/{id}/employees")
    public ResponseEntity<EmployeeDTO> createEmployee(@PathVariable Long id, @Validated @RequestBody EmployeeDTO employeeDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
        Employee employee = employeeAdapter.fromDto(employeeDTO);
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeAdapter.toDto(savedEmployee));
    }

    @GetMapping
    public ResponseEntity<Page<DepartmentDTO>> getAllDepartments(Pageable pageable,
                                                                 @RequestParam(required = false) String searchText) {
        Page<Department> departments = departmentService.getAllDepartments(pageable, searchText);
        return ResponseEntity.ok(departments.map(departmentAdapter::toDto));
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<Page<EmployeeDTO>> getEmployeesByDepartmentId(@PathVariable Long id,
                                                                        Pageable pageable,
                                                                        @RequestParam(required = false) String searchText) {
        Page<Employee> employees = employeeService.getEmployeesByDepartment(id, pageable, searchText);
        return ResponseEntity.ok(employees.map(employeeAdapter::toDto));
    }
}
