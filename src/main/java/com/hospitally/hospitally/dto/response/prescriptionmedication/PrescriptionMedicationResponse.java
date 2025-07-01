package com.hospitally.hospitally.dto.response.prescriptionmedication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionMedicationResponse {
    private Integer medicationPrescriptionId;
    private Integer prescriptionMedicationMedicationId;
    private Integer prescriptionMedicationPrescriptionId;
    private Integer prescriptionMedicationQuantity;
    private String prescriptionMedicationStatus;
    private LocalDateTime prescriptionMedicationCreatedAt;
    private LocalDateTime prescriptionMedicationUpdatedAt;
    private String message;
}
