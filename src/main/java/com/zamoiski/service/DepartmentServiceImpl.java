package com.zamoiski.service;

import com.zamoiski.dao.DepartmentDAO;
import com.zamoiski.entity.Department;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDAO departmentDAO;

    @Override
    public List<Department> findAll() {
        return departmentDAO.findAll();
    }

    @Override
    public Department findById(Long id) { return departmentDAO.findById(id); }

    @Override
    public void save(Department department) {
        departmentDAO.save(department);
    }

    @Override
    public void update(Department department) {
        departmentDAO.update(department);
    }

    @Override
    public void deleteById(Long id) {
        departmentDAO.deleteById(id);
    }
}
