package com.hospitally.hospitally.service;

import com.google.gson.Gson;
import com.hospitally.hospitally.dto.request.patient.CreatePatientRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.patient.PatientResponse;
import com.hospitally.hospitally.model.entity.Patient;
import com.hospitally.hospitally.repository.database.interfaces.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final Gson gson;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
        this.gson = new Gson();
    }


    public ApiResponse<PatientResponse> createPatient(CreatePatientRequest request) {
        // DTO → Entity
        Patient patient = gson.fromJson(gson.toJson(request), Patient.class);

        int rows = patientRepository.createPatient(patient);
        boolean isSuccess = rows > 0;

        PatientResponse response = PatientResponse.builder()
                .message(isSuccess ? "Patient created successfully" : "Failed to create patient")
                .build();

        return ApiResponse.<PatientResponse>builder()
                .statusCode(isSuccess ? "00" : "22")
                .statusMessage(isSuccess ? "Success" : "Error")
                .data(response)
                .build();
    }
}
