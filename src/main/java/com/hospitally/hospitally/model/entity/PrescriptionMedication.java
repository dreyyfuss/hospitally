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
public class PrescriptionMedication {
    private Integer medicationPrescriptionId;
    private Integer prescriptionMedicationMedicationId;
    private Integer prescriptionMedicationPrescriptionId;
    private Integer prescriptionMedicationQuantity;
    private String prescriptionMedicationStatus;
    private LocalDateTime prescriptionMedicationCreatedAt;
    private LocalDateTime prescriptionMedicationUpdatedAt;
}
