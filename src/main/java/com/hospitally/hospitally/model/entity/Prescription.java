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
public class Prescription {

    private Integer prescriptionId;
    private Integer prescriptionAppointmentId;
    private String prescriptionComment;
    private String prescriptionStatus;
    private LocalDateTime prescriptionCreatedAt;
    private LocalDateTime prescriptionUpdatedAt;

}
