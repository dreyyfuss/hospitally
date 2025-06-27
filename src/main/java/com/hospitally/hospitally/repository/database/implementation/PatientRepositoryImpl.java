package com.hospitally.hospitally.repository.database.implementation;

import com.hospitally.hospitally.model.entity.Patient;
import com.hospitally.hospitally.repository.database.interfaces.PatientRepository;
import com.hospitally.hospitally.repository.database.query.PatientQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepositoryImpl implements PatientRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PatientRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createPatient(Patient patient) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", patient.getPatientUserId())
                .addValue("gender", patient.getPatientGender())
                .addValue("dateOfBirth", patient.getPatientDateOfBirth())
                .addValue("bloodGroup", patient.getPatientBloodGroup())
                .addValue("genotype", patient.getPatientGenotype())
                .addValue("maritalStatus", patient.getPatientMaritalStatus())
                .addValue("disabilityStatus", patient.getPatientDisabilityStatus())
                .addValue("nextOfKinName", patient.getPatientNextOfKinName())
                .addValue("nextOfKinPhoneNumber", patient.getPatientNextOfKinPhoneNumber())
                .addValue("occupation", patient.getPatientOccupation())
                .addValue("languagePreference", patient.getPatientLanguagePreference());

        return jdbcTemplate.update(PatientQuery.INSERT_PATIENT, params);
    }
}
