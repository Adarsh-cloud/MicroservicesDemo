package com.example.employee_service.service;

import com.example.employee_service.client.DepartmentClient;
import com.example.employee_service.dto.DepartmentDTO;
import com.example.employee_service.dto.EmployeeDTO;
import com.example.employee_service.dto.EmployeeDTOResponse;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.exception.EmployeeNotFoundException;
import com.example.employee_service.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    RestTemplate restTemplate;
    ModelMapper modelMapper;
    EmployeeRepository employeeRepository;
    DepartmentClient departmentClient;

    @Autowired
    public EmployeeServiceImpl(RestTemplate restTemplate, ModelMapper modelMapper, EmployeeRepository employeeRepository, DepartmentClient departmentClient) {
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
        this.employeeRepository=employeeRepository;
        this.departmentClient=departmentClient;
    }

    @Override
    public EmployeeDTOResponse getEmployeeById(Integer id) {

        EmployeeDTO employeeDTO = modelMapper.map(employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id :: " + id + " doesn't exist")), EmployeeDTO.class);

       EmployeeDTOResponse employeeDTOResponse=new EmployeeDTOResponse();
       employeeDTOResponse.setEmployeeDTO(employeeDTO);
       try {
           DepartmentDTO departmentDTO = getDepartmentById(employeeDTO.getDepartmentId());

           employeeDTOResponse.setDepartmentDTO(departmentDTO);
       }
       catch (Exception e){
           employeeDTOResponse.setDepartmentDTO(null);
       }

        return employeeDTOResponse;
    }


    @Override
    public EmployeeDTOResponse getEmployeeByIdV2(Integer id) {

        EmployeeDTO employeeDTO = modelMapper.map(employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id :: " + id + " doesn't exist")), EmployeeDTO.class);

        EmployeeDTOResponse employeeDTOResponse=new EmployeeDTOResponse();
        employeeDTOResponse.setEmployeeDTO(employeeDTO);
        try {
            DepartmentDTO departmentDTO = getDepartmentByIdV2(employeeDTO.getDepartmentId());

            employeeDTOResponse.setDepartmentDTO(departmentDTO);
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
    public DepartmentDTO getDepartmentByIdV2(Integer id){
        logger.info("By fein client");
       return departmentClient.getDepartmentByIdByFein(id);
    }
}
