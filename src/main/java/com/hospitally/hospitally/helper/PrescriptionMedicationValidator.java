package com.hospitally.hospitally.helper;

import com.hospitally.hospitally.exception.AppException;
import com.hospitally.hospitally.repository.database.query.PrescriptionMedicationQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionMedicationValidator {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PrescriptionMedicationValidator(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void validateMedicationExists(int medicationId) {
        Integer count = jdbcTemplate.queryForObject(
                PrescriptionMedicationQuery.CHECK_MEDICATION_EXISTS,
                new MapSqlParameterSource("medicationId", medicationId),
                Integer.class);
        if (count == null || count == 0) {
            throw new AppException("22", "Medication does not exist or is unavailable");
        }
    }

    public void validatePrescriptionExists(int prescriptionId) {
        Integer count = jdbcTemplate.queryForObject(
                PrescriptionMedicationQuery.CHECK_PRESCRIPTION_EXISTS,
                new MapSqlParameterSource("prescriptionId", prescriptionId),
                Integer.class);
        if (count == null || count == 0) {
            throw new AppException("22", "Prescription does not exist or is inactive");
        }
    }

    public void validatePrescriptionMedicationExists(int id) {
        Integer count = jdbcTemplate.queryForObject(
                PrescriptionMedicationQuery.CHECK_PRESCRIPTION_MEDICATION_EXISTS,
                new MapSqlParameterSource("id", id),
                Integer.class);
        if (count == null || count == 0) {
            throw new AppException("22", "Prescription Medication record does not exist");
        }
    }
}
