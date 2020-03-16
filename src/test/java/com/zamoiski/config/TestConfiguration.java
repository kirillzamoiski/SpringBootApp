package com.zamoiski.config;

import com.zamoiski.dao.DepartmentDAO;
import com.zamoiski.dao.EmployeeDAO;
import com.zamoiski.dao.jdbc.DepartmentDAOImpl;
import com.zamoiski.dao.jdbc.EmployeeDAOImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import javax.sql.DataSource;

@Configuration
@PropertySources({
        @PropertySource("classpath:datasource.properties"),
        @PropertySource("classpath:employee-sql.properties"),
        @PropertySource("classpath:department-sql.properties")
})
public class TestConfiguration {

    @Value("${datasource.driverClassName}")
    private String driverClassName;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }

    @Bean
    public EmployeeDAO employeeDAO() {
        return new EmployeeDAOImpl(getDataSource());
    }
    @Bean
    public DepartmentDAO departmentDAO() { return new DepartmentDAOImpl(getDataSource()); }
}
