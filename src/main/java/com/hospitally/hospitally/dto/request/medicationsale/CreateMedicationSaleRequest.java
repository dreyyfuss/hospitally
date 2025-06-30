package com.hospitally.hospitally.dto.request.medicationsale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMedicationSaleRequest {
    private Integer saleId;
    private Integer medicationId;
    private Integer quantity;
}
