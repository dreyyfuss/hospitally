package com.hospitally.hospitally.service;

import com.hospitally.hospitally.dto.request.appointment.CreateAppointmentRequest;
import com.hospitally.hospitally.dto.request.appointment.UpdateAppointmentRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.ApiResponseBuilder;
import com.hospitally.hospitally.dto.response.appointment.AppointmentResponse;
import com.hospitally.hospitally.exception.NotFoundException;
import com.hospitally.hospitally.helper.AppointmentValidator;
import com.hospitally.hospitally.model.entity.Appointment;
import com.hospitally.hospitally.repository.database.interfaces.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;
    private final AppointmentValidator validator;

    public AppointmentService(AppointmentRepository repository, AppointmentValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public ApiResponse<AppointmentResponse> createAppointment(CreateAppointmentRequest request) {
        validator.validatePatientExists(request.getPatientId());
        validator.validateDoctorExists(request.getDoctorId());
        validator.validateDepartmentExists(request.getDepartmentId());
        validator.validatePaymentExists(request.getPaymentId());

        Appointment appointment = Appointment.builder()
                .appointmentPatientId(request.getPatientId())
                .appointmentDoctorId(request.getDoctorId())
                .appointmentDepartmentId(request.getDepartmentId())
                .appointmentPaymentId(request.getPaymentId())
                .appointmentDate(request.getAppointmentDate())
                .appointmentTime(request.getAppointmentTime())
                .appointmentDiagnosis(request.getAppointmentDiagnosis())
                .build();

        int rows = repository.createAppointment(appointment);
        if (rows > 0) {
            return ApiResponseBuilder.success(
                    AppointmentResponse.builder().message("Appointment created successfully").build(),
                    "Success"
            );
        }

        return ApiResponseBuilder.error("Failed to create appointment");
    }

    public ApiResponse<AppointmentResponse> getAppointmentById(int id) {
        return repository.findAppointmentById(id)
                .map(this::mapToResponse)
                .map(resp -> {
                    resp.setMessage("Appointment retrieved successfully");
                    return ApiResponseBuilder.success(resp, "Success");
                })
                .orElse(ApiResponseBuilder.notFound("Appointment not found"));
    }

    public ApiResponse<List<AppointmentResponse>> getAllAppointments() {
        List<Appointment> list = repository.findAllAppointments();
        return ApiResponseBuilder.success(
                list.stream().map(this::mapToResponse).toList(),
                "Success"
        );
    }

    public ApiResponse<AppointmentResponse> updateAppointment(int id, UpdateAppointmentRequest request) {
        repository.findAppointmentById(id)
                .orElseThrow(() -> new NotFoundException("Appointment not found"));

        int rows = repository.updateAppointment(id, request);
        if (rows > 0) {
            return repository.findAppointmentById(id)
                    .map(this::mapToResponse)
                    .map(res -> {
                        res.setMessage("Appointment updated successfully");
                        return ApiResponseBuilder.success(res, "Success");
                    })
                    .orElse(ApiResponseBuilder.error("Updated appointment not found"));
        }

        return ApiResponseBuilder.error("Update failed");
    }

    public ApiResponse<AppointmentResponse> deleteAppointment(int id) {
        repository.findAppointmentById(id)
                .orElseThrow(() -> new NotFoundException("Appointment not found"));

        int rows = repository.deleteAppointment(id);
        if (rows > 0) {
            return ApiResponseBuilder.success(
                    AppointmentResponse.builder().appointmentId(id).message("Appointment deleted").build(),
                    "Success"
            );
        }

        return ApiResponseBuilder.error("Failed to delete appointment");
    }

    private AppointmentResponse mapToResponse(Appointment a) {
        return AppointmentResponse.builder()
                .appointmentId(a.getAppointmentId())
                .patientId(a.getAppointmentPatientId())
                .doctorId(a.getAppointmentDoctorId())
                .departmentId(a.getAppointmentDepartmentId())
                .paymentId(a.getAppointmentPaymentId())
                .appointmentDate(a.getAppointmentDate())
                .appointmentTime(a.getAppointmentTime())
                .appointmentDiagnosis(a.getAppointmentDiagnosis())
                .appointmentStatus(a.getAppointmentStatus())
                .createdAt(a.getAppointmentCreatedAt())
                .updatedAt(a.getAppointmentUpdatedAt())
                .build();
    }
}
