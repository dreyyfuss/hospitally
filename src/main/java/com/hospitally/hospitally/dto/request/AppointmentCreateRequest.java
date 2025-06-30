package com.hospitally.hospitally.dto.request;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
@Builder
public class AppointmentCreateRequest
{
    private int appointmentPatientId;

    private Date appointmentDate;

    private Time appointmentTime;

    private int appointmentDepartmentId;

    private int appointmentDoctorId;

}
