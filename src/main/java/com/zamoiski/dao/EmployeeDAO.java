package com.zamoiski.dao;

import com.zamoiski.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(Long id);

    void save(Employee employee);

    void deleteById(Long id);

    void updateTitle(String title, String departmentName);
}