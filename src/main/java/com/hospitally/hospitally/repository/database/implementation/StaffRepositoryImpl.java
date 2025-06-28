package com.hospitally.hospitally.repository.database.implementation;

import com.hospitally.hospitally.dto.request.StaffUpdateRequest;
import com.hospitally.hospitally.model.entity.Staff;
import com.hospitally.hospitally.repository.database.interfaces.StaffRepository;
import com.hospitally.hospitally.repository.database.query.StaffQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffRepositoryImpl implements StaffRepository
{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StaffRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    //Create a staff member
    @Override
    public int createStaffMember(Staff staff)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("StaffDepartmentId", staff.getStaffDepartmentId())
        .addValue("StaffUserId", staff.getStaffUserId())
        .addValue("StaffRole", staff.getStaffRole())
        .addValue("StaffGender", staff.getStaffGender())
        .addValue("staffDateOfBirth", staff.getStaffDateOfBirth());

        return jdbcTemplate.update(StaffQuery.CREATE_STAFF, params);
    }

    //get all staff details
    @Override
    public List<Staff> getAllStaffMembers()
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbcTemplate.query(StaffQuery.GET_ALLSTAFF, params, new BeanPropertyRowMapper<>(Staff.class));
    }

    @Override
    public Staff getStaffMemberById(Long id)
    {
       MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("staffUserId", id);
       return jdbcTemplate.queryForObject(StaffQuery.GET_STAFF_BY_ID, params, new BeanPropertyRowMapper<>(Staff.class));
    }

    @Override
    public boolean updateStaffMember(Long staffId,StaffUpdateRequest request)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("staffId", staffId)
        .addValue("staffDepartmentId",request.getStaffDepartmentId())
        .addValue("staffGender", request.getStaffGender())
        .addValue("staffRole", request.getStaffRole())
        .addValue("staffDateOfBirth", request.getStaffDateOfBirth())
        .addValue("staffStatus", request.getStaffStatus());

        return jdbcTemplate.update(StaffQuery.UPDATE_STAFF, params) > 0;
    }

    @Override
    public boolean deleteStaffMember( Long id)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("staffId",id);
        return jdbcTemplate.update(StaffQuery.DELETE_STAFF, params) > 0;
    }



}
