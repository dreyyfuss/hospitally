package com.hospitally.hospitally.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private Integer paymentId;
    private LocalDateTime paymentTime;
    private String paymentReference;
    private BigDecimal paymentAmount;
    private String paymentStatus;
    private LocalDateTime paymentCreatedAt;
    private LocalDateTime paymentUpdatedAt;
}
