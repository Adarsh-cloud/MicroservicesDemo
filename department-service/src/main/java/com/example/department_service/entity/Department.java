package com.example.department_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="departments")
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

}
