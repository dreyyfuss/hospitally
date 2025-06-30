package com.hospitally.hospitally.repository.database.implementation;

import com.hospitally.hospitally.dto.request.DepartmentUpdateRequest;
import com.hospitally.hospitally.model.entity.Department;
import com.hospitally.hospitally.repository.database.interfaces.DepartmentRepository;
import com.hospitally.hospitally.repository.database.query.DepartmentQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository
{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DepartmentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int createDepartment(Department department)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("departmentId", department.getDepartmentId())
        .addValue("departmentName", department.getDepartmentName());
        return jdbcTemplate.update(DepartmentQuery.CREATE_DEPARTMENT, params);
    }

    @Override
    public List<Department> getAllDepartments()
    {
        MapSqlParameterSource params  = new MapSqlParameterSource();
        return jdbcTemplate.query(DepartmentQuery.GET_ALLDEPARTMENT,params,new BeanPropertyRowMapper<>(Department.class));
    }

    @Override
    public Department getDepartmentById(Long id)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("departmentId", id);
        return jdbcTemplate.queryForObject(DepartmentQuery.GET_DEPARTMENT_BY_ID, params, new BeanPropertyRowMapper<>(Department.class));
    }

    @Override
    public boolean updateDepartment(Long departmentId, DepartmentUpdateRequest request)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("departmentId", departmentId)
        .addValue("departmentName", request.getDepartmentName())
                .addValue("departmentStatus",request.getDepartmentStatus());
        return jdbcTemplate.update(DepartmentQuery.UPDATE_DEPARTMENT, params) > 0;
    }

    @Override
    public boolean deleteDepartment(Long id)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("departmentId",id);
        return jdbcTemplate.update(DepartmentQuery.DELETE_DEPARTMENT, params) > 0;
    }
}
