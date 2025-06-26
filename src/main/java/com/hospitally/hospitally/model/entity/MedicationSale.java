package com.hospitally.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationSale {

    private int medicationSaleId;

    private int medicationSaleSaleId;

    private int medicationSaleMedicationId;

    private double medicationSaleQuantity;

    private String medicationSaleCreatedAt;

    private String medicationSaleUpdatedAt;

    private String medicationStatus;

}
