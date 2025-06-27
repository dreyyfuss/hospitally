package com.hospitally.hospitally.service;

import com.google.gson.Gson;
import com.hospitally.hospitally.dto.request.patient.CreatePatientRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.patient.PatientResponse;
import com.hospitally.hospitally.model.entity.Patient;
import com.hospitally.hospitally.repository.database.interfaces.PatientRepository;
import com.hospitally.hospitally.repository.database.query.PatientQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final Gson gson = new Gson();

    public ApiResponse<PatientResponse> createPatient(CreatePatientRequest request) {

        Integer userId = request.getUserId();

        // FK existence check
        if (!userExists(userId)) {
            return error("User not found");
        }

        if (patientAlreadyExists(userId)) {
            return error("Patient already exists for this user");
        }

        if (userIsStaff(userId)) {
            return error("User is already registered as staff");
        }

        // Map DTO to Entity
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

        try {
            int rows = patientRepository.createPatient(patient);
            boolean success = rows > 0;

            return ApiResponse.<PatientResponse>builder()
                    .statusCode(success ? "00" : "22")
                    .statusMessage(success ? "Success" : "Error")
                    .data(PatientResponse.builder()
                            .message(success ? "Patient created successfully" : "Failed to create patient")
                            .build())
                    .build();

        } catch (DataIntegrityViolationException ex) {
            return error("Database constraint error: " + ex.getMostSpecificCause().getMessage());
        } catch (Exception ex) {
            return error("Unexpected error: " + ex.getMessage());
        }
    }

    private boolean userExists(int userId) {
        Integer count = jdbcTemplate.queryForObject(
                PatientQuery.CHECK_USER_EXISTS,
                new MapSqlParameterSource("userId", userId),
                Integer.class
        );
        return count != null && count > 0;
    }

    private boolean patientAlreadyExists(int userId) {
        Integer count = jdbcTemplate.queryForObject(
                PatientQuery.CHECK_PATIENT_EXISTS,
                new MapSqlParameterSource("userId", userId),
                Integer.class
        );
        return count != null && count > 0;
    }

    private boolean userIsStaff(int userId) {
        Integer count = jdbcTemplate.queryForObject(
                PatientQuery.CHECK_USER_IS_STAFF,
                new MapSqlParameterSource("userId", userId),
                Integer.class
        );
        return count != null && count > 0;
    }


    private ApiResponse<PatientResponse> error(String message) {
        return ApiResponse.<PatientResponse>builder()
                .statusCode("22")
                .statusMessage("Error")
                .data(PatientResponse.builder()
                        .message(message)
                        .build())
                .build();
    }
}
