package com.hospitally.hospitally.service;

import com.google.gson.Gson;
import com.hospitally.hospitally.dto.request.AppointmentCreateRequest;
import com.hospitally.hospitally.dto.request.AppointmentUpdateRequest;
import com.hospitally.hospitally.model.entity.Appointment;
import com.hospitally.hospitally.model.entity.Department;
import com.hospitally.hospitally.repository.database.interfaces.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService
{
    public final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository)
    {
        this.appointmentRepository = appointmentRepository;
    }

    public int createAppointment(AppointmentCreateRequest request)
    {
        Gson gson = new Gson();
        var appointment = gson.fromJson(gson.toJson(request), Appointment.class);
        System.out.println("AppointmentPatientId: "+ appointment);
        System.out.println("AppointmentDoctorId: "+ appointment);
        System.out.println("AppointmentDate: "+ appointment);
        System.out.println("AppointmentTime: "+ appointment);
        System.out.println("appointmentDepartmentId "+ appointment );

        return appointmentRepository.createAppointment(appointment);
    }

    public Appointment getAppointmentById(Long id)
    {
        return appointmentRepository.getAppointmentById(id);
    }

    public List<Appointment> getAllAppointments()
    {
        return appointmentRepository.getAllAppointments();
    }

    public boolean updateAppointment(Long id, AppointmentUpdateRequest request)
    {
        boolean updated = appointmentRepository.updateAppointment(id,request);
        return true;
    }

    public boolean deleteAppointment(Long id)
    {
        return appointmentRepository.deleteAppointment(id);
    }

}
