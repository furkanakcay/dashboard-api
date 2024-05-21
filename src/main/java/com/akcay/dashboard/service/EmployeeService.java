package com.akcay.dashboard.service;

import com.akcay.dashboard.entity.Employee;
import com.akcay.dashboard.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> getEmployeesByDepartment(Long departmentId, Pageable pageable, String searchText) {
        if (searchText != null && !searchText.isEmpty()) {
            return employeeRepository.findByDepartmentIdAndNameContainingIgnoreCase(departmentId, searchText, pageable);
        } else {
            return employeeRepository.findByDepartmentId(departmentId, pageable);
        }
    }
}
