package com.hospitally.hospitally.repository.database.implementation;

import com.hospitally.hospitally.dto.request.payment.UpdatePaymentRequest;
import com.hospitally.hospitally.model.entity.Payment;
import com.hospitally.hospitally.repository.database.interfaces.PaymentRepository;
import com.hospitally.hospitally.repository.database.query.PaymentQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PaymentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createPayment(Payment payment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("time", payment.getPaymentTime())
                .addValue("reference", payment.getPaymentReference())
                .addValue("amount", payment.getPaymentAmount());

        return jdbcTemplate.update(PaymentQuery.INSERT_PAYMENT, params);
    }

    @Override
    public Optional<Payment> findPaymentById(int id) {
        List<Payment> payments = jdbcTemplate.query(
                PaymentQuery.FIND_PAYMENT_BY_ID,
                new MapSqlParameterSource("id", id),
                paymentRowMapper()
        );
        return payments.stream().findFirst();
    }

    @Override
    public List<Payment> findAllPayments() {
        return jdbcTemplate.query(PaymentQuery.FIND_ALL_PAYMENTS, paymentRowMapper());
    }

    @Override
    public int updatePayment(int id, UpdatePaymentRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("amount", request.getPaymentAmount())
                .addValue("status", request.getPaymentStatus());
        return jdbcTemplate.update(PaymentQuery.UPDATE_PAYMENT, params);
    }

    @Override
    public int deletePayment(int id) {
        return jdbcTemplate.update(PaymentQuery.DELETE_PAYMENT,
                new MapSqlParameterSource("id", id));
    }

    private RowMapper<Payment> paymentRowMapper() {
        return (rs, rowNum) -> Payment.builder()
                .paymentId(rs.getInt("paymentId"))
                .paymentTime(rs.getTimestamp("paymentTime").toLocalDateTime())
                .paymentReference(rs.getString("paymentReference"))
                .paymentAmount(rs.getBigDecimal("paymentAmount"))
                .paymentStatus(rs.getString("paymentStatus"))
                .paymentCreatedAt(rs.getTimestamp("paymentCreatedAt").toLocalDateTime())
                .paymentUpdatedAt(rs.getTimestamp("paymentUpdatedAt").toLocalDateTime())
                .build();
    }
}
