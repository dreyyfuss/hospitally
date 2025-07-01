package com.hospitally.hospitally.dto.request.prescriptionmedication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePrescriptionMedicationRequest {
    private Integer medicationId;
    private Integer prescriptionId;
    private Integer quantity;
}
