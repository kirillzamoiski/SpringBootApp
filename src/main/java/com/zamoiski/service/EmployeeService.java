package com.zamoiski.service;

import com.zamoiski.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(Long id);

    void save(Employee employee);

    void update(Employee employee);

    void deleteById(Long id);

    void updateTitle(String title, String departmentName);
}
