package com.hospitally.hospitally.repository.database.interfaces;

import com.hospitally.hospitally.model.entity.Patient;

public interface PatientRepository {
    int createPatient(Patient patient);
}
