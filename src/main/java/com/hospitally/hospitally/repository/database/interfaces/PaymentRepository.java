package com.hospitally.hospitally.repository.database.interfaces;

import com.hospitally.hospitally.dto.request.payment.UpdatePaymentRequest;
import com.hospitally.hospitally.model.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    int createPayment(Payment payment);
    Optional<Payment> findPaymentById(int id);
    List<Payment> findAllPayments();
    int updatePayment(int id, UpdatePaymentRequest request);
    int deletePayment(int id);
}
