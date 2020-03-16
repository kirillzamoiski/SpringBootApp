package com.zamoiski.dao;

import com.zamoiski.entity.Department;

import java.util.List;

public interface DepartmentDAO {
    List<Department> findAll();

    Department findById(Long theId);

    void save(Department department);

    void deleteById(Long theId);
}
