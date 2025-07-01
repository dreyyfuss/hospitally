package com.hospitally.hospitally.service;

import com.hospitally.hospitally.dto.request.medicationsale.CreateMedicationSaleRequest;
import com.hospitally.hospitally.dto.request.medicationsale.UpdateMedicationSaleRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.ApiResponseBuilder;
import com.hospitally.hospitally.dto.response.medicationsale.MedicationSaleResponse;
import com.hospitally.hospitally.exception.NotFoundException;
import com.hospitally.hospitally.helper.MedicationSaleValidator;
import com.hospitally.hospitally.model.entity.MedicationSale;
import com.hospitally.hospitally.repository.database.interfaces.MedicationSaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationSaleService {

    private final MedicationSaleRepository medicationSaleRepository;
    private final MedicationSaleValidator medicationSaleValidator;

    public MedicationSaleService(MedicationSaleRepository medicationSaleRepository, MedicationSaleValidator medicationSaleValidator) {
        this.medicationSaleRepository = medicationSaleRepository;
        this.medicationSaleValidator = medicationSaleValidator;
    }

    public ApiResponse<MedicationSaleResponse> createMedicationSale(CreateMedicationSaleRequest request) {
        medicationSaleValidator.validateSaleExists(request.getSaleId());
        medicationSaleValidator.validateMedicationExists(request.getMedicationId());

        MedicationSale medicationSale = MedicationSale.builder()
                .medicationSaleSaleId(request.getSaleId())
                .medicationSaleMedicationId(request.getMedicationId())
                .medicationSaleQuantity(request.getQuantity())
                .build();

        int rows = medicationSaleRepository.createMedicationSale(medicationSale);

        if (rows > 0) {
            MedicationSaleResponse response = MedicationSaleResponse.builder()
                    .message("Medication Sale created successfully")
                    .build();
            return ApiResponseBuilder.success(response, "success");
        }

        return ApiResponseBuilder.error("Medication Sale creation failed");
    }

    public ApiResponse<MedicationSaleResponse> getMedicationSaleById(int medicationSaleId) {
        return medicationSaleRepository.findMedicationSaleById(medicationSaleId)
                .map(this::mapToResponse)
                .map(res -> {
                    res.setMessage("Medication Sale found successfully");
                    return ApiResponseBuilder.success(res, "success");
                })
                .orElse(ApiResponseBuilder.notFound("Medication Sale not found"));
    }

    public ApiResponse<List<MedicationSaleResponse>> getAllMedicationSales() {
        List<MedicationSale> medicationSales = medicationSaleRepository.findAllMedicationSales();
        List<MedicationSaleResponse> response = medicationSales.stream()
                .map(this::mapToResponse)
                .toList();

        return ApiResponseBuilder.success(response, "success");
    }

    public ApiResponse<MedicationSaleResponse> updateMedicationSale(int medicationSaleId, UpdateMedicationSaleRequest request) {
        medicationSaleRepository.findMedicationSaleById(medicationSaleId)
                .orElseThrow(() -> new NotFoundException("Medication Sale not found"));

        int rows = medicationSaleRepository.updateMedicationSale(medicationSaleId, request);

        if (rows > 0) {
            return medicationSaleRepository.findMedicationSaleById(medicationSaleId)
                    .map(this::mapToResponse)
                    .map(res -> {
                        res.setMessage("Medication Sale updated successfully");
                        return ApiResponseBuilder.success(res, "success");
                    })
                    .orElse(ApiResponseBuilder.error("Updated Medication Sale could not be retrieved"));
        }
        return ApiResponseBuilder.error("Medication Sale update failed");
    }

    public ApiResponse<MedicationSaleResponse> deleteMedicationSale(int medicationSaleId) {
        medicationSaleRepository.findMedicationSaleById(medicationSaleId)
                .orElseThrow(() -> new NotFoundException("Medication Sale not found"));

        int rows = medicationSaleRepository.deleteMedicationSale(medicationSaleId);

        if (rows > 0) {
            MedicationSaleResponse response = MedicationSaleResponse.builder()
                    .medicationSaleId(medicationSaleId)
                    .message("Medication Sale deleted successfully")
                    .build();
            return ApiResponseBuilder.success(response, "success");
        }

        return ApiResponseBuilder.error("Medication Sale not found");
    }

    private MedicationSaleResponse mapToResponse(MedicationSale medicationSale) {
        return MedicationSaleResponse.builder()
                .medicationSaleId(medicationSale.getMedicationSaleId())
                .saleId(medicationSale.getMedicationSaleSaleId())
                .medicationId(medicationSale.getMedicationSaleMedicationId())
                .quantity(medicationSale.getMedicationSaleQuantity())
                .status(medicationSale.getMedicationSaleStatus())
                .createdAt(medicationSale.getMedicationSaleCreatedAt())
                .updatedAt(medicationSale.getMedicationSaleUpdatedAt())
                .build();
    }
}
