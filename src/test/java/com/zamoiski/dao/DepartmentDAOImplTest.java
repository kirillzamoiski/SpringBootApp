package com.zamoiski.dao;

import com.zamoiski.config.TestConfiguration;
import com.zamoiski.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "classpath:insert-value.sql")
class DepartmentDAOImplTest {

    @Autowired
    DepartmentDAO departmentDAO;

    @Test
    void findAll() {
        List<Department> departments = departmentDAO.findAll();
        assertEquals(1,departments.size());
    }

    @Test
    void findById() {
        Department department = new Department("RPG",LocalDateTime.now());
        departmentDAO.save(department);
        List<Department> departments = departmentDAO.findAll();
        Department departmentRPG = departmentDAO.findById(departments.get(1).getId());
        assertEquals("RPG",departmentRPG.getDepartment_name());
    }

    @Test
    void save() {
        Department department = new Department("RTG", LocalDateTime.now());
        departmentDAO.save(department);
        List<Department> departments = departmentDAO.findAll();
        assertEquals(2,departments.size());

    }

    @Test
    void deleteById() {
        Department department = new Department("DTP",LocalDateTime.now());
        departmentDAO.save(department);
        List<Department> departments = departmentDAO.findAll();
        assertEquals(2,departments.size());
        departmentDAO.deleteById(departments.get(1).getId());
        departments = departmentDAO.findAll();
        assertEquals(1,departments.size());
    }
}