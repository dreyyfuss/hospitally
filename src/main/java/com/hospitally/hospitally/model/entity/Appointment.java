package com.hospitally.hospitally.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    private Integer appointmentId;
    private Integer appointmentPatientId;
    private Integer appointmentDoctorId;
    private Integer appointmentDepartmentId;
    private Integer appointmentPaymentId;

    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String appointmentDiagnosis;
    private String appointmentStatus;

    private LocalDateTime appointmentCreatedAt;
    private LocalDateTime appointmentUpdatedAt;
}
