package com.hospitally.hospitally.controller;

import com.hospitally.hospitally.dto.request.patient.CreatePatientRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.patient.PatientResponse;
import com.hospitally.hospitally.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PatientController {

    private final PatientService patientService;
    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/create-patient")
    public ResponseEntity<ApiResponse<PatientResponse>> createPatient(@RequestBody @Valid CreatePatientRequest request) {
        ApiResponse<PatientResponse> response = patientService.createPatient(request);
        return ResponseEntity.status(response.getStatusCode().equals
                        ("00") ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
