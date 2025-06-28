package com.hospitally.hospitally.service;

import com.hospitally.hospitally.dto.request.patient.CreatePatientRequest;
import com.hospitally.hospitally.dto.request.patient.UpdatePatientRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.ApiResponseBuilder;
import com.hospitally.hospitally.dto.response.patient.PatientResponse;
import com.hospitally.hospitally.exception.DuplicateResourceException;
import com.hospitally.hospitally.exception.NotFoundException;
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

    public ApiResponse<PatientResponse> updatePatient(int patientId, UpdatePatientRequest request) {
        patientRepository.findPatientById(patientId)
                .orElseThrow(() -> new NotFoundException("Patient not found"));

        int rows = patientRepository.updatePatient(patientId, request);

        if (rows > 0) {
            // Fetch updated patient for clean response
            Patient updatedPatient = patientRepository.findPatientById(patientId).orElse(null);

            PatientResponse response = updatedPatient != null
                    ? mapToResponse(updatedPatient)
                    : PatientResponse.builder()
                    .patientId(patientId)
                    .message("Patient updated successfully")
                    .build();
            response.setMessage("Patient updated successfully");

            return ApiResponseBuilder.success(response, "Success");
        }

        return ApiResponseBuilder.error("Failed to update patient");
    }


    public ApiResponse<PatientResponse> deletePatient(int patientId) {
        Patient patient = patientRepository.findPatientById(patientId)
                .orElseThrow(() -> new NotFoundException("Patient not found"));

        if ("INACTIVE".equalsIgnoreCase(patient.getPatientStatus())) {
            throw new DuplicateResourceException("Patient already deleted");
        }

        int rows = patientRepository.deletePatient(patientId);
        if (rows > 0) {
            return ApiResponseBuilder.success(
                    PatientResponse.builder()
                            .patientId(patientId)
                            .message("Patient deleted successfully")
                            .build(),
                    "Success"
            );
        }

        return ApiResponseBuilder.error("Failed to delete patient");
    }

}
