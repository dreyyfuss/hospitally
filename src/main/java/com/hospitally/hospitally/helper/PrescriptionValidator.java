package com.hospitally.hospitally.helper;

import com.hospitally.hospitally.exception.AppException;
import com.hospitally.hospitally.repository.database.query.PrescriptionQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionValidator {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PrescriptionValidator(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void validateAppointmentExists(int appointmentId) {
        Integer count = jdbcTemplate.queryForObject(
                PrescriptionQuery.CHECK_APPOINTMENT_EXISTS,
                new MapSqlParameterSource("appointmentId", appointmentId),
                Integer.class
        );

        if (count == null || count == 0) {
            throw new AppException("22", "Appointment does not exist or is cancelled");
        }
    }
}
