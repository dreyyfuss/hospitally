package com.hospitally.hospitally.repository.database.implementation;

import com.hospitally.hospitally.dto.request.prescriptionmedication.UpdatePrescriptionMedicationRequest;
import com.hospitally.hospitally.model.entity.PrescriptionMedication;
import com.hospitally.hospitally.repository.database.interfaces.PrescriptionMedicationRepository;
import com.hospitally.hospitally.repository.database.query.PrescriptionMedicationQuery;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PrescriptionMedicationRepositoryImpl implements PrescriptionMedicationRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PrescriptionMedicationRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createPrescriptionMedication(PrescriptionMedication prescriptionMedication) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("medicationId", prescriptionMedication.getPrescriptionMedicationMedicationId())
                .addValue("prescriptionId", prescriptionMedication.getPrescriptionMedicationPrescriptionId())
                .addValue("quantity", prescriptionMedication.getPrescriptionMedicationQuantity());

        return jdbcTemplate.update(PrescriptionMedicationQuery.INSERT_PRESCRIPTION_MEDICATION, params);
    }

    @Override
    public Optional<PrescriptionMedication> findPrescriptionMedicationById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);

        try {
            List<PrescriptionMedication> results = jdbcTemplate.query(
                    PrescriptionMedicationQuery.FIND_PRESCRIPTION_MEDICATION_BY_ID,
                    params,
                    prescriptionMedicationRowMapper()
            );
            return results.stream().findFirst();
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<PrescriptionMedication> findAllPrescriptionMedications() {
        return jdbcTemplate.query(PrescriptionMedicationQuery.FIND_ALL_PRESCRIPTION_MEDICATIONS, prescriptionMedicationRowMapper());
    }

    @Override
    public int updatePrescriptionMedication(int id, UpdatePrescriptionMedicationRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("quantity", request.getQuantity())
                .addValue("status", request.getStatus());

        return jdbcTemplate.update(PrescriptionMedicationQuery.UPDATE_PRESCRIPTION_MEDICATION, params);
    }

    @Override
    public int deletePrescriptionMedication(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);

        return jdbcTemplate.update(PrescriptionMedicationQuery.DELETE_PRESCRIPTION_MEDICATION, params);
    }

    private RowMapper<PrescriptionMedication> prescriptionMedicationRowMapper() {
        return (rs, rowNum) -> PrescriptionMedication.builder()
                .medicationPrescriptionId(rs.getInt("medicationPrescriptionId"))
                .prescriptionMedicationMedicationId(rs.getInt("prescriptionMedicationMedicationId"))
                .prescriptionMedicationPrescriptionId(rs.getInt("prescriptionMedicationPrescriptionId"))
                .prescriptionMedicationQuantity(rs.getInt("prescriptionMedicationQuantity"))
                .prescriptionMedicationStatus(rs.getString("prescriptionMedicationStatus"))
                .prescriptionMedicationCreatedAt(rs.getTimestamp("prescriptionMedicationCreatedAt").toLocalDateTime())
                .prescriptionMedicationUpdatedAt(rs.getTimestamp("prescriptionMedicationUpdatedAt").toLocalDateTime())
                .build();
    }
}
