package com.hospitally.hospitally.helper;

import com.hospitally.hospitally.exception.AppException;
import com.hospitally.hospitally.repository.database.query.PaymentQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidator {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PaymentValidator(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void validatePaymentExists(int id) {
        String sql = PaymentQuery.CHECK_PAYMENT_EXISTS;
        Integer count = jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id), Integer.class);
        if (count == null || count == 0) {
            throw new AppException("22", "Payment does not exist");
        }
    }


    public void validateUniqueReference(String reference) {
        Integer count = jdbcTemplate.queryForObject(PaymentQuery.CHECK_REFERENCE_EXISTS,
                new MapSqlParameterSource("reference", reference), Integer.class);
        if (count != null && count > 0) {
            throw new AppException("22", "Payment reference already exists");
        }
    }
}
