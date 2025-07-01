package com.hospitally.hospitally.controller;

import com.hospitally.hospitally.dto.request.appointment.CreateAppointmentRequest;
import com.hospitally.hospitally.dto.request.appointment.UpdateAppointmentRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.appointment.AppointmentResponse;
import com.hospitally.hospitally.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AppointmentResponse>> createAppointment(
            @RequestBody CreateAppointmentRequest request) {
        ApiResponse<AppointmentResponse> response = appointmentService.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AppointmentResponse>> getAppointmentById(@PathVariable int id) {
        ApiResponse<AppointmentResponse> response = appointmentService.getAppointmentById(id);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND
        ).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AppointmentResponse>> updateAppointment(
            @PathVariable int id,
            @RequestBody UpdateAppointmentRequest request) {
        ApiResponse<AppointmentResponse> response = appointmentService.updateAppointment(id, request);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        ).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<AppointmentResponse>> deleteAppointment(@PathVariable int id) {
        ApiResponse<AppointmentResponse> response = appointmentService.deleteAppointment(id);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        ).body(response);
    }
}
