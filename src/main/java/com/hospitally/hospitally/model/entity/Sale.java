package com.hospitally.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sale {

    private int saleId;

    private int salePaymentId;

    private int salePatientId;

    private String saleStatus;

    private String saleCreatedAt;

    private String saleUpdatedAt;

}
