package com.hospitally.hospitally.repository.database.implementation;

import com.hospitally.hospitally.dto.request.patient.UpdatePatientRequest;
import com.hospitally.hospitally.model.entity.Patient;
import com.hospitally.hospitally.repository.database.interfaces.PatientRepository;
import com.hospitally.hospitally.repository.database.query.PatientQuery;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Patient> findPatientById(int id) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource("id", id);
            Patient patient = jdbcTemplate.queryForObject(
                    PatientQuery.GET_PATIENT_BY_ID, params, patientRowMapper());
            return Optional.ofNullable(patient);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Patient> findAllPatients() {
        return jdbcTemplate.query(PatientQuery.GET_ALL_PATIENTS, patientRowMapper());
    }

    private RowMapper<Patient> patientRowMapper() {
        return (rs, rowNum) -> Patient.builder()
                .patientId(rs.getInt("patientId"))
                .patientUserId(rs.getInt("patientUserId"))
                .patientGender(rs.getString("patientGender"))
                .patientDateOfBirth(rs.getDate("patientDateOfBirth").toLocalDate())
                .patientBloodGroup(rs.getString("patientBloodGroup"))
                .patientGenotype(rs.getString("patientGenotype"))
                .patientMaritalStatus(rs.getString("patientMaritalStatus"))
                .patientDisabilityStatus(rs.getString("patientDisabilityStatus"))
                .patientNextOfKinName(rs.getString("patientNextOfKinName"))
                .patientNextOfKinPhoneNumber(rs.getString("patientNextOfKinPhoneNumber"))
                .patientOccupation(rs.getString("patientOccupation"))
                .patientLanguagePreference(rs.getString("patientLanguagePreference"))
                .patientStatus(rs.getString("patientStatus"))
                .patientCreatedAt(rs.getTimestamp("patientCreatedAt").toLocalDateTime())
                .patientUpdatedAt(rs.getTimestamp("patientUpdatedAt").toLocalDateTime())
                .build();
    }


    @Override
    public int updatePatient(int patientId, UpdatePatientRequest request) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("patientId", patientId)
                .addValue("gender", request.getGender())
                .addValue("dateOfBirth", request.getDateOfBirth())
                .addValue("bloodGroup", request.getBloodGroup())
                .addValue("genotype", request.getGenotype())
                .addValue("maritalStatus", request.getMaritalStatus())
                .addValue("disabilityStatus", request.getDisabilityStatus())
                .addValue("nextOfKinName", request.getNextOfKinName())
                .addValue("nextOfKinPhoneNumber", request.getNextOfKinPhoneNumber())
                .addValue("occupation", request.getOccupation())
                .addValue("languagePreference", request.getLanguagePreference());

        return jdbcTemplate.update(PatientQuery.UPDATE_PATIENT, params);
    }

}
