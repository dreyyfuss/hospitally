package com.hospitally.hospitally.dto.request.medicationsale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMedicationSaleRequest {
    private Integer quantity;
    private String status;
}
