package com.hospitally.hospitally.service;

import com.hospitally.hospitally.dto.request.patient.CreatePatientRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.ApiResponseBuilder;
import com.hospitally.hospitally.dto.response.patient.PatientResponse;
import com.hospitally.hospitally.helper.PatientValidator;
import com.hospitally.hospitally.model.entity.Patient;
import com.hospitally.hospitally.repository.database.interfaces.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientValidator validator;

    public PatientService(PatientRepository patientRepository, PatientValidator validator) {
        this.patientRepository = patientRepository;
        this.validator = validator;
    }

    public ApiResponse<PatientResponse> createPatient(CreatePatientRequest request) {
        validator.validateUserExists(request.getUserId());
        validator.validateUserIsNotStaff(request.getUserId());
        validator.validateUserNotAlreadyPatient(request.getUserId());

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
            return ApiResponseBuilder.success(PatientResponse.builder().message(
                    "Patient created successfully").build(), "Success");
        }

        return ApiResponseBuilder.error("Could not insert patient");
    }

    public ApiResponse<PatientResponse> getPatientById(int id) {
        return patientRepository.findPatientById(id)
                .map(patient -> ApiResponseBuilder.success(mapToResponse(patient), "Success"))
                .orElse(ApiResponseBuilder.notFound("Patient not found"));
    }

    public ApiResponse<List<PatientResponse>> getAllPatients() {
        List<PatientResponse> responses = patientRepository.findAllPatients()
                .stream().map(this::mapToResponse).toList();

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
