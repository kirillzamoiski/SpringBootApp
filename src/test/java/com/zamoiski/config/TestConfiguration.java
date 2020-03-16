package com.zamoiski.config;

import com.zamoiski.dao.DepartmentDAO;
import com.zamoiski.dao.EmployeeDAO;
import com.zamoiski.dao.jdbc.DepartmentDAOImpl;
import com.zamoiski.dao.jdbc.EmployeeDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@PropertySources({
        @PropertySource("classpath:employee-sql.properties"),
        @PropertySource("classpath:department-sql.properties")
})
public class TestConfiguration {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("classpath:create-table.sql").build();
    }

    @Bean
    public EmployeeDAO employeeDAO() {
        return new EmployeeDAOImpl(dataSource());
    }

    @Bean
    public DepartmentDAO departmentDAO() {
        return new DepartmentDAOImpl(dataSource());
    }
}
