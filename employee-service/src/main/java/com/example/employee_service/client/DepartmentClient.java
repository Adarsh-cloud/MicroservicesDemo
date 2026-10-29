package com.example.employee_service.client;

import com.example.employee_service.dto.DepartmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="department-service",url = "${department.service.url}")
public interface DepartmentClient {
    @GetMapping("/departments/{id}")
    public DepartmentDTO getDepartmentByIdByFein(@PathVariable("id") Integer id);
}
