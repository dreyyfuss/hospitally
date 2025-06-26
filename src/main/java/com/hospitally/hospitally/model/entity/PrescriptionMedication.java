package com.hospitally.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrescriptionMedication {

    private int prescriptionMedicationId;

    private int prescriptionMedicationMedicationId;

    private int prescriptionMedicationPrescriptionId;

    private double prescriptionMedicationQuantity;

    private String prescriptionMedicationCreatedAt;

    private String prescriptionMedicationUpdatedAt;

    private String prescriptionMedicationStatus;

}
