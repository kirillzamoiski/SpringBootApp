package com.zamoiski.dao.jdbc;

import com.zamoiski.dao.DepartmentDAO;
import com.zamoiski.entity.Department;
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
@PropertySource("classpath:department-sql.properties")
public class DepartmentDAOImpl implements DepartmentDAO {

    @Value("${insertDepartment}")
    private String insert;
    @Value("${updateDepartment}")
    private String update;
    @Value("${selectAllDepartments}")
    private String selectAll;
    @Value("${findByIdDepartment}")
    private String findById;
    @Value("${deleteByIdDepartment}")
    private String deleteById;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<Department> departmentRowMapper = ((resultSet, i) ->
            new Department(resultSet.getLong("id"),
            resultSet.getString("department_name"),
            resultSet.getTimestamp("date_of_create").toLocalDateTime()));

    public DepartmentDAOImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Department> findAll() {
        return namedParameterJdbcTemplate.query(selectAll, departmentRowMapper);
    }

    @Override
    public Department findById(Long id) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(findById, namedParameter, departmentRowMapper);
    }

    @Override
    public void save(Department department) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("department_name", department.getDepartmentName());
        namedParameters.addValue("date_of_create", department.getDateOfCreate());
        namedParameterJdbcTemplate.update(insert, namedParameters);
    }

    @Override
    public void update(Department department) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", department.getId());
        namedParameters.addValue("department_name", department.getDepartmentName());
        namedParameters.addValue("date_of_create", department.getDateOfCreate());
        namedParameterJdbcTemplate.update(update, namedParameters);
    }

    @Override
    public void deleteById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(deleteById, namedParameters);
    }
}
