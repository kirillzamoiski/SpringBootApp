package com.zamoiski.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    private Long id;
    private String departmentName;
    private LocalDateTime dateOfCreate;

    public Department(String departmentName, LocalDateTime dateOfCreate) {
        this.departmentName = departmentName;
        this.dateOfCreate = dateOfCreate;
    }
}
