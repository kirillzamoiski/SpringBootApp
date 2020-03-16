package com.zamoiski.service;

import com.zamoiski.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();

    Department findById(Long id);

    void save(Department department);

    void update(Department department);

    void deleteById(Long id);
}
