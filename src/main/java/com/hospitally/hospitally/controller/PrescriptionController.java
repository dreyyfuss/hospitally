package com.hospitally.hospitally.controller;

import com.hospitally.hospitally.dto.request.prescription.CreatePrescriptionRequest;
import com.hospitally.hospitally.dto.request.prescription.UpdatePrescriptionRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.prescription.PrescriptionResponse;
import com.hospitally.hospitally.service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PrescriptionResponse>> createPrescription(
            @RequestBody CreatePrescriptionRequest request
    ) {
        ApiResponse<PrescriptionResponse> response = prescriptionService.createPrescription(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PrescriptionResponse>> getPrescriptionById(@PathVariable int id) {
        ApiResponse<PrescriptionResponse> response = prescriptionService.getPrescriptionById(id);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND
        ).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<PrescriptionResponse>>> getAllPrescriptions() {
        ApiResponse<List<PrescriptionResponse>> response = prescriptionService.getAllPrescriptions();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PrescriptionResponse>> updatePrescription(
            @PathVariable int id,
            @RequestBody UpdatePrescriptionRequest request
    ) {
        ApiResponse<PrescriptionResponse> response = prescriptionService.updatePrescription(id, request);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        ).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PrescriptionResponse>> deletePrescription(@PathVariable int id) {
        ApiResponse<PrescriptionResponse> response = prescriptionService.deletePrescription(id);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        ).body(response);
    }
}
