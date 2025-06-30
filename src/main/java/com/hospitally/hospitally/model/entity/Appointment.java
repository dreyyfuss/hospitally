package com.hospitally.hospitally.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    private int appointmentId;

    private int appointmentPaymentId;

    private int appointmentPatientId;

    private int appointmentDoctorId;

    private int appointmentDepartmentId;

    private String appointmentDate;

    private String appointmentTime;

    private String appointmentDiagnosis;

    private String appointmentStatus;

    private String appointmentCreatedAt;

    private String appointmentUpdatedAt;

}