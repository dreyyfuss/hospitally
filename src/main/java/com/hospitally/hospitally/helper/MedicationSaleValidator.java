package com.hospitally.hospitally.helper;

import com.hospitally.hospitally.exception.AppException;
import com.hospitally.hospitally.repository.database.query.MedicationSaleQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MedicationSaleValidator {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MedicationSaleValidator(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void validateSaleExists(int saleId) {
        Integer count = jdbcTemplate.queryForObject(
                MedicationSaleQuery.CHECK_SALE_EXISTS,
                new MapSqlParameterSource("saleId", saleId),
                Integer.class
        );
        if (count == null || count == 0) {
            throw new AppException("22", "Sale does not exist");
        }
    }

    public void validateMedicationExists(int medicationId) {
        Integer count = jdbcTemplate.queryForObject(
                MedicationSaleQuery.CHECK_MEDICATION_EXISTS,
                new MapSqlParameterSource("medicationId", medicationId),
                Integer.class
        );
        if (count == null || count == 0) {
            throw new AppException("22", "Medication does not exist");
        }
    }
}
