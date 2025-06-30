package com.hospitally.hospitally.dto.response.appointment;

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
public class AppointmentResponse {
    private Integer appointmentId;
    private Integer patientId;
    private Integer doctorId;
    private Integer departmentId;
    private Integer paymentId;

    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String appointmentDiagnosis;
    private String appointmentStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String message;
}
