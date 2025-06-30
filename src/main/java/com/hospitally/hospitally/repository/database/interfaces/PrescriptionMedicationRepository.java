package com.hospitally.hospitally.repository.database.interfaces;

import com.hospitally.hospitally.dto.request.prescriptionmedication.UpdatePrescriptionMedicationRequest;
import com.hospitally.hospitally.model.entity.PrescriptionMedication;

import java.util.List;
import java.util.Optional;

public interface PrescriptionMedicationRepository {
    int createPrescriptionMedication(PrescriptionMedication prescriptionMedication);
    Optional<PrescriptionMedication> findPrescriptionMedicationById(int id);
    List<PrescriptionMedication> findAllPrescriptionMedications();
    int updatePrescriptionMedication(int id, UpdatePrescriptionMedicationRequest request);
    int deletePrescriptionMedication(int id);
}
