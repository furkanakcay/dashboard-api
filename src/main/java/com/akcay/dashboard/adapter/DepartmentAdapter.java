package com.akcay.dashboard.adapter;

import com.akcay.dashboard.dto.DepartmentDTO;
import com.akcay.dashboard.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentAdapter {

    public DepartmentDTO toDto(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        return dto;
    }
}
