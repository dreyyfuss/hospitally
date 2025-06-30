package com.hospitally.hospitally.helper;

import com.hospitally.hospitally.exception.AppException;
import com.hospitally.hospitally.repository.database.query.AppointmentQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppointmentValidator {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AppointmentValidator(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void validatePatientExists(int patientId) {
        Integer count = jdbcTemplate.queryForObject(
                AppointmentQuery.CHECK_PATIENT_EXISTS,
                new MapSqlParameterSource("patientId", patientId),
                Integer.class
        );
        if (count == null || count == 0) {
            throw new AppException("22", "Patient does not exist");
        }
    }

    public void validateDoctorExists(int doctorId) {
        Integer count = jdbcTemplate.queryForObject(
                AppointmentQuery.CHECK_DOCTOR_EXISTS,
                new MapSqlParameterSource("doctorId", doctorId),
                Integer.class
        );
        if (count == null || count == 0) {
            throw new AppException("22", "Doctor does not exist");
        }
    }

    public void validateDepartmentExists(int departmentId) {
        Integer count = jdbcTemplate.queryForObject(
                AppointmentQuery.CHECK_DEPARTMENT_EXISTS,
                new MapSqlParameterSource("departmentId", departmentId),
                Integer.class
        );
        if (count == null || count == 0) {
            throw new AppException("22", "Department does not exist");
        }
    }

    public void validatePaymentExists(int paymentId) {
        Integer count = jdbcTemplate.queryForObject(
                AppointmentQuery.CHECK_PAYMENT_EXISTS,
                new MapSqlParameterSource("paymentId", paymentId),
                Integer.class
        );
        if (count == null || count == 0) {
            throw new AppException("22", "Payment does not exist");
        }
    }
}
