package com.example.department_service.controller;

import com.example.department_service.dto.DepartmentDTO;
import com.example.department_service.entity.Department;
import com.example.department_service.exception.DepartmentNotFoundException;
import com.example.department_service.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    private ModelMapper modelMapper= new ModelMapper();
    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentDTO departmentDTO){
        return new ResponseEntity<>(departmentRepository.save(modelMapper.map(departmentDTO,Department.class)), HttpStatus.CREATED);

    }
    @GetMapping("/departments/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Integer id){
        Optional<Department> departmentOpt = departmentRepository.findById(id);
        if(departmentOpt.isPresent()){
            return new ResponseEntity<>(modelMapper.map(departmentOpt.get(),DepartmentDTO.class),HttpStatus.OK);
        }
        else{
            throw new DepartmentNotFoundException("Department with id :: "+id+" not found");
        }

    }


}
