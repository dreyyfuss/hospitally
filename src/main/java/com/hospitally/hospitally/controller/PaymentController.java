package com.hospitally.hospitally.controller;

import com.hospitally.hospitally.dto.request.payment.CreatePaymentRequest;
import com.hospitally.hospitally.dto.request.payment.UpdatePaymentRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.payment.PaymentResponse;
import com.hospitally.hospitally.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentResponse>> createPayment(@RequestBody CreatePaymentRequest request) {
        var response = service.createPayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentResponse>> getPaymentById(@PathVariable int id) {
        var response = service.getPaymentById(id);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getAllPayments() {
        return ResponseEntity.ok(service.getAllPayments());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentResponse>> updatePayment(
            @PathVariable int id, @RequestBody UpdatePaymentRequest request) {
        var response = service.updatePayment(id, request);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentResponse>> deletePayment(@PathVariable int id) {
        var response = service.deletePayment(id);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(response);
    }
}
