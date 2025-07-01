package com.hospitally.hospitally.dto.request.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAppointmentRequest {
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String appointmentDiagnosis;
    private String appointmentStatus;
}
