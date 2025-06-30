package com.hospitally.hospitally.dto.request.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {
    private BigDecimal paymentAmount;
    private String paymentStatus;
}
