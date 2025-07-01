package com.hospitally.hospitally.repository.database.interfaces;

import com.hospitally.hospitally.dto.request.prescription.UpdatePrescriptionRequest;
import com.hospitally.hospitally.model.entity.Prescription;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository {

    int createPrescription(Prescription prescription);

    Optional<Prescription> findPrescriptionById(int prescriptionId);

    List<Prescription> findAllPrescriptions();

    int updatePrescription(int prescriptionId, UpdatePrescriptionRequest request);

    int deletePrescription(int prescriptionId);
}
