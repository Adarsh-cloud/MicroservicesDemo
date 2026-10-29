package com.example.employee_service.dto;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EmployeeDTOResponse {
    private EmployeeDTO employeeDTO;
    private DepartmentDTO departmentDTO;
}
