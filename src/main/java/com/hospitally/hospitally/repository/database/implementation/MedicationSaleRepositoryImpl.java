package com.hospitally.hospitally.repository.database.implementation;

import com.hospitally.hospitally.dto.request.medicationsale.UpdateMedicationSaleRequest;
import com.hospitally.hospitally.model.entity.MedicationSale;
import com.hospitally.hospitally.repository.database.interfaces.MedicationSaleRepository;
import com.hospitally.hospitally.repository.database.query.MedicationSaleQuery;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MedicationSaleRepositoryImpl implements MedicationSaleRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MedicationSaleRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createMedicationSale(MedicationSale medicationSale) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("saleId", medicationSale.getMedicationSaleSaleId())
                .addValue("medicationId", medicationSale.getMedicationSaleMedicationId())
                .addValue("quantity", medicationSale.getMedicationSaleQuantity());

        return jdbcTemplate.update(MedicationSaleQuery.INSERT_MEDICATION_SALE, params);
    }

    @Override
    public Optional<MedicationSale> findMedicationSaleById(int medicationSaleId) {
        MapSqlParameterSource params = new MapSqlParameterSource("medicationSaleId", medicationSaleId);

        try {
            List<MedicationSale> results = jdbcTemplate.query(
                    MedicationSaleQuery.FIND_MEDICATION_SALE_BY_ID,
                    params,
                    medicationSaleRowMapper()
            );
            return results.stream().findFirst();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<MedicationSale> findAllMedicationSales() {
        return jdbcTemplate.query(MedicationSaleQuery.FIND_ALL_MEDICATION_SALES, medicationSaleRowMapper());
    }

    @Override
    public int updateMedicationSale(int medicationSaleId, UpdateMedicationSaleRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("medicationSaleId", medicationSaleId)
                .addValue("quantity", request.getQuantity())
                .addValue("status", request.getStatus());

        return jdbcTemplate.update(MedicationSaleQuery.UPDATE_MEDICATION_SALE, params);
    }

    @Override
    public int deleteMedicationSale(int medicationSaleId) {
        MapSqlParameterSource params = new MapSqlParameterSource("medicationSaleId", medicationSaleId);

        return jdbcTemplate.update(MedicationSaleQuery.DELETE_MEDICATION_SALE, params);
    }

    private RowMapper<MedicationSale> medicationSaleRowMapper() {
        return (rs, rowNum) -> MedicationSale.builder()
                .medicationSaleId(rs.getInt("medicationSaleId"))
                .medicationSaleSaleId(rs.getInt("medicationSaleSaleId"))
                .medicationSaleMedicationId(rs.getInt("medicationSaleMedicationId"))
                .medicationSaleQuantity(rs.getInt("medicationSaleQuantity"))
                .medicationSaleStatus(rs.getString("medicationSaleStatus"))
                .medicationSaleCreatedAt(rs.getTimestamp("medicationSaleCreatedAt").toLocalDateTime())
                .medicationSaleUpdatedAt(rs.getTimestamp("medicationSaleUpdatedAt").toLocalDateTime())
                .build();
    }
}
