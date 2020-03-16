package com.zamoiski.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private JobTitle jobTitle;
    private String gender;
    private LocalDateTime dateOfBirth;
    private Department department;

    public Employee(String firstName, String lastName, JobTitle jobTitle, String gender, LocalDateTime dateOfBirth, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
    }
}
