package com.example.employee_service.service;

import com.example.employee_service.dto.DepartmentDTO;
import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.dto.EmployeeDTOResponse;

public interface EmployeeService {
    public EmployeeDTOResponse getEmployeeById(Integer id);
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    public DepartmentDTO getDepartmentById(Integer id);
}
