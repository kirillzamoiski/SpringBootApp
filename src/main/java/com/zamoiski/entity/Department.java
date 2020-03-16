package com.zamoiski.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="department")
@Data
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "department_name")
    private String department_name;

    @Column(name = "date_of_create")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfCreate;


    public Department(Long id,String department_name, LocalDateTime dateOfCreate) {
        this.id = id;
        this.department_name = department_name;
        this.dateOfCreate = dateOfCreate;
    }

    public Department(String department_name, LocalDateTime dateOfCreate) {
        this.department_name = department_name;
        this.dateOfCreate = dateOfCreate;
    }
}
