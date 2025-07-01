package com.hospitally.hospitally.service;

import com.hospitally.hospitally.dto.request.prescription.CreatePrescriptionRequest;
import com.hospitally.hospitally.dto.request.prescription.UpdatePrescriptionRequest;
import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.ApiResponseBuilder;
import com.hospitally.hospitally.dto.response.prescription.PrescriptionResponse;
import com.hospitally.hospitally.exception.NotFoundException;
import com.hospitally.hospitally.helper.PrescriptionValidator;
import com.hospitally.hospitally.model.entity.Prescription;
import com.hospitally.hospitally.repository.database.interfaces.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionValidator prescriptionValidator;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, PrescriptionValidator prescriptionValidator) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionValidator = prescriptionValidator;
    }

    public ApiResponse<PrescriptionResponse> createPrescription(CreatePrescriptionRequest request) {
        prescriptionValidator.validateAppointmentExists(request.getAppointmentId());

        Prescription prescription = Prescription.builder()
                .prescriptionAppointmentId(request.getAppointmentId())
                .prescriptionComment(request.getComment())
                .build();

        int rows = prescriptionRepository.createPrescription(prescription);

        if (rows > 0) {
            PrescriptionResponse response = PrescriptionResponse.builder()
                    .message("Prescription created successfully")
                    .build();
            return ApiResponseBuilder.success(response, "success");
        }

        return ApiResponseBuilder.error("Prescription creation failed");
    }

    public ApiResponse<PrescriptionResponse> getPrescriptionById(int prescriptionId) {
        return prescriptionRepository.findPrescriptionById(prescriptionId)
                .map(this::mapToResponse)
                .map(res -> {
                    res.setMessage("Prescription found successfully");
                    return ApiResponseBuilder.success(res, "success");
                })
                .orElse(ApiResponseBuilder.notFound("Prescription not found"));
    }

    public ApiResponse<List<PrescriptionResponse>> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionRepository.findAllPrescriptions();
        List<PrescriptionResponse> response = prescriptions.stream()
                .map(this::mapToResponse)
                .toList();

        return ApiResponseBuilder.success(response, "success");
    }

    public ApiResponse<PrescriptionResponse> updatePrescription(int prescriptionId, UpdatePrescriptionRequest request) {
        prescriptionRepository.findPrescriptionById(prescriptionId)
                .orElseThrow(() -> new NotFoundException("Prescription not found"));

        int rows = prescriptionRepository.updatePrescription(prescriptionId, request);

        if (rows > 0) {
            return prescriptionRepository.findPrescriptionById(prescriptionId)
                    .map(this::mapToResponse)
                    .map(res -> {
                        res.setMessage("Prescription updated successfully");
                        return ApiResponseBuilder.success(res, "success");
                    })
                    .orElse(ApiResponseBuilder.error("Updated Prescription could not be retrieved"));
        }

        return ApiResponseBuilder.error("Prescription update failed");
    }

    public ApiResponse<PrescriptionResponse> deletePrescription(int prescriptionId) {
        prescriptionRepository.findPrescriptionById(prescriptionId)
                .orElseThrow(() -> new NotFoundException("Prescription not found"));

        int rows = prescriptionRepository.deletePrescription(prescriptionId);

        if (rows > 0) {
            PrescriptionResponse response = PrescriptionResponse.builder()
                    .prescriptionId(prescriptionId)
                    .message("Prescription deleted successfully")
                    .build();
            return ApiResponseBuilder.success(response, "success");
        }

        return ApiResponseBuilder.error("Prescription deletion failed");
    }

    private PrescriptionResponse mapToResponse(Prescription prescription) {
        return PrescriptionResponse.builder()
                .prescriptionId(prescription.getPrescriptionId())
                .appointmentId(prescription.getPrescriptionAppointmentId())
                .comment(prescription.getPrescriptionComment())
                .status(prescription.getPrescriptionStatus())
                .createdAt(prescription.getPrescriptionCreatedAt())
                .updatedAt(prescription.getPrescriptionUpdatedAt())
                .build();
    }
}
