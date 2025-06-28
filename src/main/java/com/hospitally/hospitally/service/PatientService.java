package com.hospitally.hospitally.service;

import com.hospitally.hospitally.dto.request.patient.CreatePatientRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.ApiResponseBuilder;
import com.hospitally.hospitally.dto.response.patient.PatientResponse;
import com.hospitally.hospitally.model.entity.Patient;
import com.hospitally.hospitally.repository.database.interfaces.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public ApiResponse<PatientResponse> createPatient(CreatePatientRequest request) {
        Patient patient = Patient.builder()
                .patientUserId(request.getUserId())
                .patientGender(request.getGender())
                .patientDateOfBirth(request.getDateOfBirth())
                .patientBloodGroup(request.getBloodGroup())
                .patientGenotype(request.getGenotype())
                .patientMaritalStatus(request.getMaritalStatus())
                .patientDisabilityStatus(request.getDisabilityStatus())
                .patientNextOfKinName(request.getNextOfKinName())
                .patientNextOfKinPhoneNumber(request.getNextOfKinPhoneNumber())
                .patientOccupation(request.getOccupation())
                .patientLanguagePreference(request.getLanguagePreference())
                .build();

        int rows = patientRepository.createPatient(patient);

        if (rows > 0) {
            PatientResponse response = PatientResponse.builder()
                    .message("Patient created successfully")
                    .build();
            return ApiResponseBuilder.success(response, "Success");
        } else {
            return ApiResponseBuilder.error("Patient creation failed");
        }
    }

    public ApiResponse<PatientResponse> getPatientById(int id) {
        return patientRepository.findPatientById(id)
                .map(p -> ApiResponseBuilder.success(mapToResponse(p), "Success"))
                .orElse(ApiResponseBuilder.notFound("Patient not found"));
    }

    public ApiResponse<List<PatientResponse>> getAllPatients() {
        List<Patient> patients = patientRepository.findAllPatients();
        List<PatientResponse> responses = patients.stream()
                .map(this::mapToResponse)
                .toList();

        return ApiResponseBuilder.success(responses, "Success");
    }

    private PatientResponse mapToResponse(Patient p) {
        return PatientResponse.builder()
                .patientId(p.getPatientId())
                .userId(p.getPatientUserId())
                .gender(p.getPatientGender())
                .dateOfBirth(p.getPatientDateOfBirth())
                .bloodGroup(p.getPatientBloodGroup())
                .genotype(p.getPatientGenotype())
                .maritalStatus(p.getPatientMaritalStatus())
                .disabilityStatus(p.getPatientDisabilityStatus())
                .nextOfKinName(p.getPatientNextOfKinName())
                .nextOfKinPhoneNumber(p.getPatientNextOfKinPhoneNumber())
                .occupation(p.getPatientOccupation())
                .languagePreference(p.getPatientLanguagePreference())
                .status(p.getPatientStatus())
                .createdAt(p.getPatientCreatedAt())
                .updatedAt(p.getPatientUpdatedAt())
                .build();
    }
}
