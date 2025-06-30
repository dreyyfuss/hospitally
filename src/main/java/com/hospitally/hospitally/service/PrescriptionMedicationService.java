package com.hospitally.hospitally.service;

import com.hospitally.hospitally.dto.request.prescriptionmedication.CreatePrescriptionMedicationRequest;
import com.hospitally.hospitally.dto.request.prescriptionmedication.UpdatePrescriptionMedicationRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.ApiResponseBuilder;
import com.hospitally.hospitally.dto.response.prescriptionmedication.PrescriptionMedicationResponse;
import com.hospitally.hospitally.helper.PrescriptionMedicationValidator;
import com.hospitally.hospitally.model.entity.PrescriptionMedication;
import com.hospitally.hospitally.repository.database.interfaces.PrescriptionMedicationRepository;
import com.hospitally.hospitally.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionMedicationService {
    private final PrescriptionMedicationRepository repository;
    private final PrescriptionMedicationValidator validator;

    public PrescriptionMedicationService(PrescriptionMedicationRepository repository, PrescriptionMedicationValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public ApiResponse<PrescriptionMedicationResponse> createPrescriptionMedication(CreatePrescriptionMedicationRequest request) {
        validator.validateMedicationExists(request.getMedicationId());
        validator.validatePrescriptionExists(request.getPrescriptionId());

        PrescriptionMedication pm = PrescriptionMedication.builder()
                .prescriptionMedicationMedicationId(request.getMedicationId())
                .prescriptionMedicationPrescriptionId(request.getPrescriptionId())
                .prescriptionMedicationQuantity(request.getQuantity())
                .build();

        int rows = repository.createPrescriptionMedication(pm);
        if (rows > 0) {
            PrescriptionMedicationResponse response = PrescriptionMedicationResponse.builder()
                    .message("Prescription Medication created successfully")
                    .build();
            return ApiResponseBuilder.success(response, "success");
        }
        return ApiResponseBuilder.error("Failed to create Prescription Medication");
    }

    public ApiResponse<PrescriptionMedicationResponse> getPrescriptionMedicationById(int id) {
        return repository.findPrescriptionMedicationById(id)
                .map(this::mapToResponse)
                .map(res -> {
                    res.setMessage("Prescription Medication found successfully");
                    return ApiResponseBuilder.success(res, "success");
                })
                .orElse(ApiResponseBuilder.notFound("Prescription Medication not found"));
    }

    public ApiResponse<List<PrescriptionMedicationResponse>> getAllPrescriptionMedications() {
        List<PrescriptionMedication> list = repository.findAllPrescriptionMedications();
        List<PrescriptionMedicationResponse> responses = list.stream()
                .map(this::mapToResponse)
                .toList();
        return ApiResponseBuilder.success(responses, "success");
    }

    public ApiResponse<PrescriptionMedicationResponse> updatePrescriptionMedication(int id, UpdatePrescriptionMedicationRequest request) {
        validator.validatePrescriptionMedicationExists(id);

        int rows = repository.updatePrescriptionMedication(id, request);
        if (rows > 0) {
            return repository.findPrescriptionMedicationById(id)
                    .map(this::mapToResponse)
                    .map(res -> {
                        res.setMessage("Prescription Medication updated successfully");
                        return ApiResponseBuilder.success(res, "success");
                    })
                    .orElse(ApiResponseBuilder.error("Updated Prescription Medication could not be retrieved"));
        }
        return ApiResponseBuilder.error("Failed to update Prescription Medication");
    }

    public ApiResponse<PrescriptionMedicationResponse> deletePrescriptionMedication(int id) {
        validator.validatePrescriptionMedicationExists(id);

        int rows = repository.deletePrescriptionMedication(id);
        if (rows > 0) {
            PrescriptionMedicationResponse response = PrescriptionMedicationResponse.builder()
                    .medicationPrescriptionId(id)
                    .message("Prescription Medication deleted successfully")
                    .build();
            return ApiResponseBuilder.success(response, "success");
        }
        return ApiResponseBuilder.error("Failed to delete Prescription Medication");
    }

    private PrescriptionMedicationResponse mapToResponse(PrescriptionMedication pm) {
        return PrescriptionMedicationResponse.builder()
                .medicationPrescriptionId(pm.getMedicationPrescriptionId())
                .prescriptionMedicationMedicationId(pm.getPrescriptionMedicationMedicationId())
                .prescriptionMedicationPrescriptionId(pm.getPrescriptionMedicationPrescriptionId())
                .prescriptionMedicationQuantity(pm.getPrescriptionMedicationQuantity())
                .prescriptionMedicationStatus(pm.getPrescriptionMedicationStatus())
                .prescriptionMedicationCreatedAt(pm.getPrescriptionMedicationCreatedAt())
                .prescriptionMedicationUpdatedAt(pm.getPrescriptionMedicationUpdatedAt())
                .build();
    }
}
