package com.zamoiski.dao.jdbc;

import com.zamoiski.dao.DepartmentDAO;
import com.zamoiski.entity.Department;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@PropertySource("department-sql.properties")
public class DepartmentDAOImpl implements DepartmentDAO {

    @Value("${Insert}")
    private  String insert;
    @Value("${selectAll}")
    private String selectAll;
    @Value("${findById}")
    private String findById;
    @Value("${deleteById}")
    private String deleteById;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DepartmentDAOImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Department> findAll() {
        return namedParameterJdbcTemplate.query(selectAll, new DepartmentMapper());
    }

    @Override
    public Department findById(Long theId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id",Long.valueOf(theId));
        return namedParameterJdbcTemplate.queryForObject(findById, namedParameters, new DepartmentMapper());
    }

    @Override
    public void save(Department department) {
        Map namedParameters = new HashMap();
        namedParameters.put("department_name",department.getDepartment_name());
        namedParameters.put("date_of_create",department.getDateOfCreate());
        namedParameterJdbcTemplate.update(insert,namedParameters);
    }

    @Override
    public void deleteById(Long theId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id",Long.valueOf(theId));
        namedParameterJdbcTemplate.update(deleteById, namedParameters);
    }
}
