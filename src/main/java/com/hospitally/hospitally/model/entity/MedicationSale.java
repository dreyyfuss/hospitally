package com.hospitally.hospitally.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationSale {
    private Integer medicationSaleId;
    private Integer medicationSaleSaleId;
    private Integer medicationSaleMedicationId;
    private Integer medicationSaleQuantity;
    private String medicationSaleStatus;
    private LocalDateTime medicationSaleCreatedAt;
    private LocalDateTime medicationSaleUpdatedAt;
}
