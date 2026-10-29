package com.example.employee_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private String name;
    private Integer departmentId;
}
