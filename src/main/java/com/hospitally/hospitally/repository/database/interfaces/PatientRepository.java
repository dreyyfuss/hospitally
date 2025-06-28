package com.hospitally.hospitally.repository.database.interfaces;

import com.hospitally.hospitally.dto.request.patient.UpdatePatientRequest;
import com.hospitally.hospitally.model.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    int createPatient(Patient patient);
    List<Patient> findAllPatients();
    Optional<Patient> findPatientById(int id);
    int updatePatient(int patientId, UpdatePatientRequest request);
    int deletePatient(int patientId);


}
