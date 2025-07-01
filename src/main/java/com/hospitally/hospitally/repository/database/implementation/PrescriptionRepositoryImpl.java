package com.hospitally.hospitally.repository.database.implementation;

import com.hospitally.hospitally.dto.request.prescription.UpdatePrescriptionRequest;
import com.hospitally.hospitally.model.entity.Prescription;
import com.hospitally.hospitally.repository.database.interfaces.PrescriptionRepository;
import com.hospitally.hospitally.repository.database.query.PrescriptionQuery;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PrescriptionRepositoryImpl implements PrescriptionRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PrescriptionRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createPrescription(Prescription prescription) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentId", prescription.getPrescriptionAppointmentId())
                .addValue("comment", prescription.getPrescriptionComment());

        return jdbcTemplate.update(PrescriptionQuery.INSERT_PRESCRIPTION, params);
    }

    @Override
    public Optional<Prescription> findPrescriptionById(int prescriptionId) {
        MapSqlParameterSource params = new MapSqlParameterSource("prescriptionId", prescriptionId);

        try {
            List<Prescription> result = jdbcTemplate.query(
                    PrescriptionQuery.FIND_PRESCRIPTION_BY_ID,
                    params,
                    prescriptionRowMapper()
            );
            return result.stream().findFirst();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Prescription> findAllPrescriptions() {
        return jdbcTemplate.query(PrescriptionQuery.FIND_ALL_PRESCRIPTIONS, prescriptionRowMapper());
    }

    @Override
    public int updatePrescription(int prescriptionId, UpdatePrescriptionRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("prescriptionId", prescriptionId)
                .addValue("comment", request.getComment())
                .addValue("status", request.getStatus());

        return jdbcTemplate.update(PrescriptionQuery.UPDATE_PRESCRIPTION, params);
    }

    @Override
    public int deletePrescription(int prescriptionId) {
        MapSqlParameterSource params = new MapSqlParameterSource("prescriptionId", prescriptionId);
        return jdbcTemplate.update(PrescriptionQuery.DELETE_PRESCRIPTION, params);
    }

    private RowMapper<Prescription> prescriptionRowMapper() {
        return (rs, rowNum) -> Prescription.builder()
                .prescriptionId(rs.getInt("prescriptionId"))
                .prescriptionAppointmentId(rs.getInt("prescriptionAppointmentId"))
                .prescriptionComment(rs.getString("prescriptionComment"))
                .prescriptionStatus(rs.getString("prescriptionStatus"))
                .prescriptionCreatedAt(rs.getTimestamp("prescriptionCreatedAt").toLocalDateTime())
                .prescriptionUpdatedAt(rs.getTimestamp("prescriptionUpdatedAt").toLocalDateTime())
                .build();
    }
}
