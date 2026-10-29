package com.example.employee_service.service;

import com.example.employee_service.dto.DepartmentDTO;
import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.dto.EmployeeDTOResponse;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.exception.EmployeeNotFoundException;
import com.example.employee_service.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    RestTemplate restTemplate;
    ModelMapper modelMapper;
    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(RestTemplate restTemplate, ModelMapper modelMapper,EmployeeRepository employeeRepository) {
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
        this.employeeRepository=employeeRepository;
    }

    @Override
    public EmployeeDTOResponse getEmployeeById(Integer id) {

        EmployeeDTO employeeDTO = modelMapper.map(employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id :: " + id + " doesn't exist")), EmployeeDTO.class);

       EmployeeDTOResponse employeeDTOResponse=new EmployeeDTOResponse();
       employeeDTOResponse.setEmployeeDTO(employeeDTO);
       try {
           DepartmentDTO departmentDTO = getDepartmentById(employeeDTO.getDepartmentId());

           employeeDTOResponse.setDepartmentDTO(getDepartmentById(employeeDTO.getDepartmentId()));
       }
       catch (Exception e){
           employeeDTOResponse.setDepartmentDTO(null);
       }

        return employeeDTOResponse;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        return modelMapper.map(employeeRepository.save(modelMapper.map(employeeDTO, Employee.class)),EmployeeDTO.class);
    }

    @Override
    public DepartmentDTO getDepartmentById(Integer id) {
        DepartmentDTO response = restTemplate.getForObject("http://localhost:8081/api/departments/"+id, DepartmentDTO.class);
        return response;
    }
}
