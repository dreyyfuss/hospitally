package com.hospitally.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Prescription {

    private int prescriptionId;

    private int prescriptionAppointmentId;

    private String prescriptionName;

    private String prescriptionCreatedAt;

    private String prescriptionUpdatedAt;

    private String prescriptionStatus;

}
