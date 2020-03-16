package com.zamoiski.dao.jdbc;

import com.zamoiski.dao.EmployeeDAO;
import com.zamoiski.entity.Department;
import com.zamoiski.entity.Employee;
import com.zamoiski.entity.JobTitle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@PropertySource("classpath:employee-sql.properties")
public class EmployeeDAOImpl implements EmployeeDAO {

    @Value("${insertEmployee}")
    private String insert;
    @Value("${updateEmployee}")
    private String update;
    @Value("${selectAllEmployees}")
    private String selectAll;
    @Value("${findByIdEmployee}")
    private String findById;
    @Value("${deleteByIdEmployee}")
    private String deleteById;
    @Value("${findIdByNameEmployee}")
    private String findIdByName;
    @Value("${updateTitleByDepartmentId}")
    private String updateTitleById;


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<Employee> rowMapperEmployee = ((resultSet, i) ->
            new Employee(resultSet.getLong("employee_id"), resultSet.getString("first_name"),
            resultSet.getString("last_name"), JobTitle.valueOf(resultSet.getString("job_name")),
            resultSet.getString("gender"), resultSet.getTimestamp("date_of_birth").toLocalDateTime(),
            new Department(resultSet.getLong("department_id"), resultSet.getString("department_name"), resultSet.getTimestamp("date_of_create").toLocalDateTime())
            ));

    public EmployeeDAOImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> findAll() {
        return namedParameterJdbcTemplate.query(selectAll, rowMapperEmployee);
    }

    @Override
    public Employee findById(Long id) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(findById, namedParameter, rowMapperEmployee);
    }

    @Override
    public void save(Employee employee) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        setNamedParameters(employee, namedParameters);
        namedParameterJdbcTemplate.update(insert, namedParameters);
    }

    @Override
    public void update(Employee employee) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("employee_id", employee.getId());
        setNamedParameters(employee, namedParameters);
        namedParameterJdbcTemplate.update(update, namedParameters);
    }

    @Override
    public void deleteById(Long id) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(deleteById, namedParameter);
    }

    @Override
    public void updateTitle(String title, String departmentName) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("departmentName", departmentName);
        Department departmentByName = namedParameterJdbcTemplate.queryForObject(findIdByName, namedParameters, ((resultSet, i) -> new Department(resultSet.getLong("id"), resultSet.getString("department_name"), resultSet.getTimestamp("date_of_create").toLocalDateTime())));
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        namedParameter.addValue("title", title);
        namedParameter.addValue("id", departmentByName.getId());
        namedParameterJdbcTemplate.update(updateTitleById, namedParameter);
    }

    private void setNamedParameters(Employee employee, MapSqlParameterSource namedParameters) {
        namedParameters.addValue("first_name", employee.getFirstName());
        namedParameters.addValue("last_name", employee.getLastName());
        namedParameters.addValue("job_name", employee.getJobTitle().name());
        namedParameters.addValue("gender", employee.getGender());
        namedParameters.addValue("date_of_birth", employee.getDateOfBirth());
        namedParameters.addValue("department_id", employee.getDepartment().getId());
    }
}
