package com.zamoiski.dao;

import com.zamoiski.config.TestConfiguration;
import com.zamoiski.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:insert-value.sql")
class DepartmentDAOImplTest {

    @Autowired
    DepartmentDAO departmentDAO;

    @Test
    void findAll() {
        List<Department> departments = departmentDAO.findAll();
        assertEquals(2, departments.size());
    }

    @Test
    void findById() {
        Department departmentRPG = departmentDAO.findById(1L);
        assertEquals("MMO", departmentRPG.getDepartmentName());
    }

    @Test
    void save() {
        Department department = new Department("RTG", LocalDateTime.now());
        departmentDAO.save(department);
        assertEquals(3, departmentDAO.findAll().size());

    }

    @Test
    void deleteById() {
        departmentDAO.deleteById(2L);
        List<Department> departments = departmentDAO.findAll();
        assertEquals(1, departments.size());
    }
}