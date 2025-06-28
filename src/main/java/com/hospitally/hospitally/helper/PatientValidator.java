package com.hospitally.hospitally.helper;

import com.hospitally.hospitally.exception.DuplicateResourceException;
import com.hospitally.hospitally.exception.ForbiddenException;
import com.hospitally.hospitally.exception.NotFoundException;
import com.hospitally.hospitally.repository.database.query.PatientQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PatientValidator {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PatientValidator(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void validateUserExists(int userId) {
        Integer count = jdbcTemplate.queryForObject(
                PatientQuery.CHECK_USER_EXISTS,
                new MapSqlParameterSource("userId", userId),
                Integer.class
        );

        if (count == null || count == 0) {
            throw new NotFoundException("User does not exist");
        }
    }

    public void validateUserIsNotStaff(int userId) {
        Integer count = jdbcTemplate.queryForObject(
                PatientQuery.CHECK_USER_IS_STAFF,
                new MapSqlParameterSource("userId", userId),
                Integer.class
        );

        if (count != null && count > 0) {
            throw new ForbiddenException("User is already registered as staff");
        }
    }

    public void validateUserNotAlreadyPatient(int userId) {
        Integer count = jdbcTemplate.queryForObject(
                PatientQuery.CHECK_PATIENT_EXISTS,
                new MapSqlParameterSource("userId", userId),
                Integer.class
        );

        if (count != null && count > 0) {
            throw new DuplicateResourceException("Patient already exists for this user");
        }
    }
}
