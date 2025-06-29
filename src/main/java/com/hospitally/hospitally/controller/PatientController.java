package com.hospitally.hospitally.controller;

import com.hospitally.hospitally.dto.request.patient.CreatePatientRequest;
import com.hospitally.hospitally.dto.request.patient.UpdatePatientRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.patient.PatientResponse;
import com.hospitally.hospitally.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    private final PatientService patientService;
    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PatientResponse>> createPatient(@RequestBody @Valid CreatePatientRequest request) {
        ApiResponse<PatientResponse> response = patientService.createPatient(request);
        return ResponseEntity.status(
                response.getStatusCode().equals("00")
                        ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientResponse>> getPatientById(@PathVariable int id) {
        ApiResponse<PatientResponse> response = patientService.getPatientById(id);
        HttpStatus status = response.getStatusCode().equals("00")
                ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PatientResponse>>> getAllPatients() {
        ApiResponse<List<PatientResponse>> response = patientService.getAllPatients();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientResponse>> updatePatient(
            @PathVariable int id,
            @RequestBody @Valid UpdatePatientRequest request) {
        ApiResponse<PatientResponse> response = patientService.updatePatient(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientResponse>> deletePatient(@PathVariable int id) {
        ApiResponse<PatientResponse> response = patientService.deletePatient(id);
        return ResponseEntity.ok(response);
    }

}
