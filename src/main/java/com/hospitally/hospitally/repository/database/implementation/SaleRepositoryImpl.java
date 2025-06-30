package com.hospitally.hospitally.repository.database.implementation;

import com.hospitally.hospitally.dto.request.SaleUpdateRequest;
import com.hospitally.hospitally.model.entity.Sale;
import com.hospitally.hospitally.repository.database.interfaces.SaleRepository;
import com.hospitally.hospitally.repository.database.query.SaleQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaleRepositoryImpl implements SaleRepository
{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SaleRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int createSale(Sale sale)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("saleId", sale.getSaleId())
                .addValue("salePaymentId", sale.getSalePaymentId())
                .addValue("salePatientId", sale.getSalePatientId());
        return jdbcTemplate.update(SaleQuery.CREATE_SALE, params);
    }

    @Override
    public List<Sale> getAllSales()
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbcTemplate.query(SaleQuery.GET_ALLSALES,params,new BeanPropertyRowMapper<>(Sale.class));
    }

    @Override
    public Sale getSaleById(Long id)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("saleId", id);
        return jdbcTemplate.queryForObject(SaleQuery.GET_SALE_BY_ID, params, new BeanPropertyRowMapper<>(Sale.class));
    }

    @Override
    public boolean updateSale(Long saleId, SaleUpdateRequest request)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("saleId",saleId)
                .addValue("saleStatus", request.getSaleStatus());
        return jdbcTemplate.update(SaleQuery.UPDATE_SALE, params) > 0;
    }

    @Override
    public boolean deleteSale(Long id)
    {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("saleId",id);
        return jdbcTemplate.update(SaleQuery.DELETE_SALE, params) > 0;
    }
}
