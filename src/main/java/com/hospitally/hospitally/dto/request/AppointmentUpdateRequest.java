package com.hospitally.hospitally.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentUpdateRequest
{
    private String appointmentDiagnosis;

    private String appointmentDate;

    private String appointmentTime;

    private String appointmentStatus;

    private int appointmentPaymentId;

    private int appointmentPatientId;

    private int appointmentDoctorId;

    private int appointmentDepartmentId;
}
