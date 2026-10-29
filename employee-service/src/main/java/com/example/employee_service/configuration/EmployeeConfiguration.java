package com.example.employee_service.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EmployeeConfiguration {

    @Bean(name = "restTemplate")
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }

    @Bean(name="modelMapper")
    public ModelMapper getModelMapper(){
        return  new ModelMapper();
    }
}
