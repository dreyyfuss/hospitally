package com.hospitally.hospitally.service;

import com.hospitally.hospitally.dto.request.doctor.CreateDoctorRequest;
import com.hospitally.hospitally.dto.request.doctor.UpdateDoctorRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.ApiResponseBuilder;
import com.hospitally.hospitally.dto.response.doctor.DoctorResponse;
import com.hospitally.hospitally.exception.NotFoundException;
import com.hospitally.hospitally.helper.DoctorValidator;
import com.hospitally.hospitally.model.entity.Doctor;
import com.hospitally.hospitally.repository.database.interfaces.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorValidator doctorValidator;

    public DoctorService(DoctorRepository doctorRepository, DoctorValidator doctorValidator) {
        this.doctorRepository = doctorRepository;
        this.doctorValidator = doctorValidator;
    }

    public ApiResponse<DoctorResponse> createDoctor(CreateDoctorRequest request) {
        doctorValidator.validateStaffExist(request.getStaffId());
        doctorValidator.validateDoctorNotExist(request.getStaffId());

        Doctor doctor = Doctor.builder()
                .doctorStaffId(request.getStaffId())
                .doctorSpecialization(request.getSpecialization())
                .build();

        int rows = doctorRepository.createDoctor(doctor);

        if (rows > 0) {
            DoctorResponse response = DoctorResponse.builder()
                    .message("Doctor created successfully")
                    .build();
            return ApiResponseBuilder.success(response, "success");
        }

        return ApiResponseBuilder.error("Doctor creation failed");
    }

    public ApiResponse<DoctorResponse> getDoctorById(int doctorId) {
        return doctorRepository.findDoctorById(doctorId)
                .map(this::mapToResponse)
                .map(res -> {
                    res.setMessage("Doctor found successfully");
                    return ApiResponseBuilder.success(res, "success");
                })
                .orElse(ApiResponseBuilder.notFound("Doctor not found"));
    }

    public ApiResponse<List<DoctorResponse>> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAllDoctors();
        List<DoctorResponse> response = doctors.stream()
                .map(this::mapToResponse)
                .toList();

        return ApiResponseBuilder.success(response, "success");
    }

    public ApiResponse<DoctorResponse> updateDoctor(int doctorId, UpdateDoctorRequest request) {
        doctorRepository.findDoctorById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));

        int rows = doctorRepository.updateDoctor(doctorId, request);

        if (rows > 0) {
            return doctorRepository.findDoctorById(doctorId)
                    .map(this::mapToResponse)
                    .map(res -> {
                        res.setMessage("Doctor updated successfully");
                        return ApiResponseBuilder.success(res, "success");
                    })
                    .orElse(ApiResponseBuilder.error("Updated Doctor could not be retrieved"));
        }
        return ApiResponseBuilder.error("Doctor not found");
    }

    public ApiResponse<DoctorResponse> deleteDoctor(int doctorId) {
        doctorRepository.findDoctorById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));

        int rows = doctorRepository.deleteDoctor(doctorId);

        if (rows > 0) {
            DoctorResponse response = DoctorResponse.builder()
                    .doctorId(doctorId)
                    .message("Doctor deleted successfully")
                    .build();
            return ApiResponseBuilder.success(response, "success");
        }

        return ApiResponseBuilder.error("Doctor not found");
    }

    private DoctorResponse mapToResponse(Doctor doctor) {
        return DoctorResponse.builder()
                .doctorId(doctor.getDoctorId())
                .staffId(doctor.getDoctorStaffId())
                .specialization(doctor.getDoctorSpecialization())
                .status(doctor.getDoctorStatus())
                .createdAt(doctor.getDoctorCreatedAt())
                .updatedAt(doctor.getDoctorUpdatedAt())
                .build();
    }

}
