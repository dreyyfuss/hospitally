package com.hospitally.hospitally.repository.database.interfaces;

import com.hospitally.hospitally.dto.request.appointment.UpdateAppointmentRequest;
import com.hospitally.hospitally.model.entity.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    int createAppointment(Appointment appointment);
    Optional<Appointment> findAppointmentById(int id);
    List<Appointment> findAllAppointments();
    int updateAppointment(int id, UpdateAppointmentRequest request);
    int deleteAppointment(int id);
}
