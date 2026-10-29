package com.example.employee_service.service;

import com.example.employee_service.dto.DepartmentDTO;
import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.dto.EmployeeDTOResponse;

public interface EmployeeService {
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    public EmployeeDTOResponse getEmployeeById(Integer id);
    public DepartmentDTO getDepartmentById(Integer id);
    EmployeeDTOResponse getEmployeeByIdV2(Integer id);
    public DepartmentDTO getDepartmentByIdV2(Integer id);





}
