package com.hospitally.hospitally.repository.database.implementation;

import com.hospitally.hospitally.dto.request.EquipmentUpdateRequest;
import com.hospitally.hospitally.model.entity.Department;
import com.hospitally.hospitally.model.entity.Equipment;
import com.hospitally.hospitally.repository.database.interfaces.EquipmentRepository;
import com.hospitally.hospitally.repository.database.query.EquipmentQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EquipmentRepositoryImpl implements EquipmentRepository
{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public EquipmentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createEquipment(Equipment equipment)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("equipmentId", equipment.getEquipmentId())
        .addValue("equipmentName", equipment.getEquipmentName())
        .addValue("equipmentQuantity", equipment.getEquipmentQuantity());

        return jdbcTemplate.update(EquipmentQuery.CREATE_EQUIPMENT, params);
    }

    @Override
    public List<Equipment> getAllEquipments()
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbcTemplate.query(EquipmentQuery.GET_ALLEQUIPMENTS,params,new BeanPropertyRowMapper<>(Equipment.class));
    }

    @Override
    public Equipment getEquipmentById(Long id)
    {
       MapSqlParameterSource params = new MapSqlParameterSource()
       .addValue("equipmentId", id);
       return jdbcTemplate.queryForObject(EquipmentQuery.GET_DEPARTMENT_BY_ID,params,new BeanPropertyRowMapper<>(Equipment.class));
    }

    @Override
    public boolean updateEquipment(Long equipmentId, EquipmentUpdateRequest request)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("equipmentId", equipmentId)
        .addValue("equipmentStatus",request.getEquipmentStatus());

        return jdbcTemplate.update(EquipmentQuery.UPDATE_EQUIPMENT, params) > 0;
    }

    @Override
    public boolean deleteEquipment(Long id)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("equipmentId",id);
        return jdbcTemplate.update(EquipmentQuery.DELETE_EQUIPMENT, params) > 0;
    }

}
