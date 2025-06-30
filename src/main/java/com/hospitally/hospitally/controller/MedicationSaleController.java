package com.hospitally.hospitally.controller;

import com.hospitally.hospitally.dto.request.medicationsale.CreateMedicationSaleRequest;
import com.hospitally.hospitally.dto.request.medicationsale.UpdateMedicationSaleRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.medicationsale.MedicationSaleResponse;
import com.hospitally.hospitally.service.MedicationSaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicationsale")
public class MedicationSaleController {

    private final MedicationSaleService medicationSaleService;

    public MedicationSaleController(MedicationSaleService medicationSaleService) {
        this.medicationSaleService = medicationSaleService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MedicationSaleResponse>> createMedicationSale(@RequestBody CreateMedicationSaleRequest request) {
        ApiResponse<MedicationSaleResponse> response = medicationSaleService.createMedicationSale(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicationSaleResponse>> getMedicationSaleById(@PathVariable int id) {
        ApiResponse<MedicationSaleResponse> response = medicationSaleService.getMedicationSaleById(id);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.NOT_FOUND
        ).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<MedicationSaleResponse>>> getAllMedicationSales() {
        ApiResponse<List<MedicationSaleResponse>> response = medicationSaleService.getAllMedicationSales();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicationSaleResponse>> updateMedicationSale(
            @PathVariable int id,
            @RequestBody UpdateMedicationSaleRequest request) {
        ApiResponse<MedicationSaleResponse> response = medicationSaleService.updateMedicationSale(id, request);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        ).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicationSaleResponse>> deleteMedicationSale(@PathVariable int id) {
        ApiResponse<MedicationSaleResponse> response = medicationSaleService.deleteMedicationSale(id);
        return ResponseEntity.status(
                response.getStatusCode().equals("00") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        ).body(response);
    }
}
