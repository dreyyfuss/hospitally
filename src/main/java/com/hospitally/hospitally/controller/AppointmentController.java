package com.hospitally.hospitally.controller;

import com.hospitally.hospitally.dto.request.AppointmentCreateRequest;
import com.hospitally.hospitally.dto.request.AppointmentUpdateRequest;
import com.hospitally.hospitally.model.entity.Appointment;
import com.hospitally.hospitally.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController
{
    public final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService)
    {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<String> createAppointment(@RequestBody AppointmentCreateRequest request)
    {
        appointmentService.createAppointment(request);
        return ResponseEntity.ok("Appointment created successfully");
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id)
    {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping
    public List<Appointment> getAllAppointments()
    {
        return appointmentService.getAllAppointments();
    }

    @PutMapping
    public ResponseEntity<String> updateAppointment(@PathVariable Long id,@RequestBody AppointmentUpdateRequest request)
    {
        boolean updated = appointmentService.updateAppointment(id,request);
        return updated ? ResponseEntity.ok("Appointment updated successfully") : ResponseEntity.badRequest().body("Invalid Input");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id)
    {
        boolean deleted = appointmentService.deleteAppointment(id);
        return deleted ? ResponseEntity.ok("Appointment deleted successfully") : ResponseEntity.badRequest().body("Invalid Input");
    }

}
