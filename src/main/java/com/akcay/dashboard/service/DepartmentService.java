package com.akcay.dashboard.service;

import com.akcay.dashboard.entity.Department;
import com.akcay.dashboard.entity.Employee;
import com.akcay.dashboard.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Page<Department> getAllDepartments(Pageable pageable, String searchText) {
        if (searchText != null && !searchText.isEmpty()) {
            return departmentRepository.findByNameContainingIgnoreCase(searchText, pageable);
        } else {
            return departmentRepository.findAll(pageable);
        }
    }
}
