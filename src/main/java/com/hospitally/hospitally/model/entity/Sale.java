package com.hospitally.hospitally.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    private int saleId;

    private int salePaymentId;

    private int salePatientId;

    private String saleStatus;

    private String saleCreatedAt;

    private String saleUpdatedAt;

}
