package com.hospitally.hospitally.service;

import com.hospitally.hospitally.dto.request.payment.CreatePaymentRequest;
import com.hospitally.hospitally.dto.request.payment.UpdatePaymentRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.ApiResponseBuilder;
import com.hospitally.hospitally.dto.response.payment.PaymentResponse;
import com.hospitally.hospitally.helper.PaymentValidator;
import com.hospitally.hospitally.model.entity.Payment;
import com.hospitally.hospitally.repository.database.interfaces.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentValidator paymentValidator;

    public PaymentService(PaymentRepository paymentRepository, PaymentValidator paymentValidator) {
        this.paymentRepository = paymentRepository;
        this.paymentValidator = paymentValidator;
    }

    public ApiResponse<PaymentResponse> createPayment(CreatePaymentRequest request) {
        paymentValidator.validateUniqueReference(request.getPaymentReference());

        Payment payment = Payment.builder()
                .paymentTime(request.getPaymentTime())
                .paymentReference(request.getPaymentReference())
                .paymentAmount(request.getPaymentAmount())
                .build();

        int rows = paymentRepository.createPayment(payment);

        if (rows > 0) {
            return ApiResponseBuilder.success(
                    PaymentResponse.builder().message("Payment created successfully").build(), "Success");
        }
        return ApiResponseBuilder.error("Payment creation failed");
    }

    public ApiResponse<PaymentResponse> getPaymentById(int id) {
        paymentValidator.validatePaymentExists(id);

        return paymentRepository.findPaymentById(id)
                .map(this::mapToResponse)
                .map(res -> {
                    res.setMessage("Payment found successfully");
                    return ApiResponseBuilder.success(res, "Success");
                })
                .orElse(ApiResponseBuilder.notFound("Payment not found"));
    }

    public ApiResponse<List<PaymentResponse>> getAllPayments() {
        List<Payment> payments = paymentRepository.findAllPayments();
        List<PaymentResponse> response = payments.stream().map(this::mapToResponse).toList();
        return ApiResponseBuilder.success(response, "Success");
    }

    public ApiResponse<PaymentResponse> updatePayment(int id, UpdatePaymentRequest request) {
        paymentValidator.validatePaymentExists(id);

        int rows = paymentRepository.updatePayment(id, request);

        if (rows > 0) {
            return paymentRepository.findPaymentById(id)
                    .map(this::mapToResponse)
                    .map(res -> {
                        res.setMessage("Payment updated successfully");
                        return ApiResponseBuilder.success(res, "Success");
                    })
                    .orElse(ApiResponseBuilder.error("Updated payment could not be retrieved"));
        }
        return ApiResponseBuilder.error("Payment update failed");
    }

    public ApiResponse<PaymentResponse> deletePayment(int id) {
        paymentValidator.validatePaymentExists(id);

        int rows = paymentRepository.deletePayment(id);

        if (rows > 0) {
            return ApiResponseBuilder.success(
                    PaymentResponse.builder().paymentId(id).message("Payment deleted successfully").build(), "Success");
        }

        return ApiResponseBuilder.error("Payment deletion failed");
    }

    private PaymentResponse mapToResponse(Payment payment) {
        return PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .paymentTime(payment.getPaymentTime())
                .paymentReference(payment.getPaymentReference())
                .paymentAmount(payment.getPaymentAmount())
                .paymentStatus(payment.getPaymentStatus())
                .createdAt(payment.getPaymentCreatedAt())
                .updatedAt(payment.getPaymentUpdatedAt())
                .build();
    }
}
