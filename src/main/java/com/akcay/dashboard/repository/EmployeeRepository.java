package com.akcay.dashboard.repository;

import com.akcay.dashboard.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);
    Page<Employee> findByDepartmentIdAndNameContainingIgnoreCase(Long departmentId, String name, Pageable pageable);

}
