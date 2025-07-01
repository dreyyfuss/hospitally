package com.hospitally.hospitally.controller;

import com.hospitally.hospitally.dto.request.prescriptionmedication.CreatePrescriptionMedicationRequest;
import com.hospitally.hospitally.dto.request.prescriptionmedication.UpdatePrescriptionMedicationRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.prescriptionmedication.PrescriptionMedicationResponse;
import com.hospitally.hospitally.service.PrescriptionMedicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prescription-medication")
public class PrescriptionMedicationController {
    private final PrescriptionMedicationService service;

    public PrescriptionMedicationController(PrescriptionMedicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PrescriptionMedicationResponse>> create(
            @RequestBody CreatePrescriptionMedicationRequest request) {
        ApiResponse<PrescriptionMedicationResponse> response = service.createPrescriptionMedication(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PrescriptionMedicationResponse>> getById(@PathVariable int id) {
        ApiResponse<PrescriptionMedicationResponse> response = service.getPrescriptionMedicationById(id);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND
        ).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<PrescriptionMedicationResponse>>> getAll() {
        ApiResponse<List<PrescriptionMedicationResponse>> response = service.getAllPrescriptionMedications();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PrescriptionMedicationResponse>> update(
            @PathVariable int id,
            @RequestBody UpdatePrescriptionMedicationRequest request) {
        ApiResponse<PrescriptionMedicationResponse> response = service.updatePrescriptionMedication(id, request);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        ).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PrescriptionMedicationResponse>> delete(@PathVariable int id) {
        ApiResponse<PrescriptionMedicationResponse> response = service.deletePrescriptionMedication(id);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        ).body(response);
    }
}
