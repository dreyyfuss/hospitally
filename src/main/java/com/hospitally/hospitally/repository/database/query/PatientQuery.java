package com.hospitally.hospitally.repository.database.query;

public class PatientQuery {


    public static final String INSERT_PATIENT = """
        INSERT INTO HO_PATIENT (
            patientUserId, patientGender, patientDateOfBirth, patientBloodGroup, patientGenotype,
            patientMaritalStatus, patientDisabilityStatus, patientNextOfKinName, patientNextOfKinPhoneNumber,
            patientOccupation, patientLanguagePreference, patientStatus, patientCreatedAt, patientUpdatedAt
        ) VALUES (
            :userId, :gender, :dateOfBirth, :bloodGroup, :genotype, :maritalStatus, :disabilityStatus,
            :nextOfKinName, :nextOfKinPhoneNumber, :occupation, :languagePreference, 'ACTIVE', GETDATE(), GETDATE()
        )
    """;
}
