package com.hospitally.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {

    private int paymentId;

    private int paymentSaleId;

    private String paymentStatus;

    private String paymentTime;

    private String paymentReference;

    private String paymentCreatedAt;

    private String paymentUpdatedAt;

}
