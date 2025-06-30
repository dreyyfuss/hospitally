package com.hospitally.hospitally.repository.database.interfaces;

import com.hospitally.hospitally.dto.request.AppointmentUpdateRequest;
import com.hospitally.hospitally.model.entity.Appointment;

import java.util.List;

public interface AppointmentRepository
{
    public int createAppointment(Appointment appointment);
    public Appointment getAppointmentById(Long id);
    List<Appointment> getAllAppointments();
    boolean updateAppointment(Long appointmentId, AppointmentUpdateRequest request);
    boolean deleteAppointment( Long id);
}
