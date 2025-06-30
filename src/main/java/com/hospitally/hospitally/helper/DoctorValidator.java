package com.hospitally.hospitally.helper;

import com.hospitally.hospitally.exception.AppException;
import com.hospitally.hospitally.repository.database.query.DoctorQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DoctorValidator {
    public final NamedParameterJdbcTemplate jdbcTemplate;

    public DoctorValidator(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void validateStaffExist(int staffId) {

        Integer count = jdbcTemplate.queryForObject(DoctorQuery.CHECK_STAFF_EXISTS,
                new MapSqlParameterSource("staffId", staffId), Integer.class);

        if (count == null || count == 0) {
            throw new AppException("22", "Staff does not exist");
        }
    }

    public void validateDoctorNotExist(int staffId) {

        Integer count = jdbcTemplate.queryForObject(DoctorQuery.CHECK_DOCTOR_EXISTS, new MapSqlParameterSource(
                "staffId", staffId), Integer.class);

        if (count != null && count > 0) {
            throw new AppException("22", "This Staff is already a Doctor");
        }
    }
}
