package com.zamoiski.service;

import com.zamoiski.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(Long theId);

    void save(Employee employee);

    void deleteById(Long theId);
}
