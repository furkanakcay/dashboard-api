package com.akcay.dashboard.adapter;

import com.akcay.dashboard.dto.EmployeeDTO;
import com.akcay.dashboard.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAdapter {

    public EmployeeDTO toDto(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setTitle(employee.getTitle());
        dto.setSalary(employee.getSalary());
        dto.setGender(employee.getGender());
        return dto;
    }

    public Employee fromDto(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setTitle(employeeDTO.getTitle());
        employee.setSalary(employeeDTO.getSalary());
        employee.setGender(employeeDTO.getGender());
        return employee;
    }
}
