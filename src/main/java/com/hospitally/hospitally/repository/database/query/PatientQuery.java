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

    public static final String CHECK_USER_EXISTS = """
        SELECT COUNT(*) FROM HO_USER WHERE userId = :userId
    """;

    public static final String CHECK_PATIENT_EXISTS = """
        SELECT COUNT(*) FROM HO_PATIENT WHERE patientUserId = :userId
    """;

    public static final String CHECK_USER_IS_STAFF = """
        SELECT COUNT(*) FROM HO_STAFF WHERE staffUserId = :userId
    """;

    public static final String GET_PATIENT_BY_ID = """
        SELECT * FROM HO_PATIENT WHERE patientId = :id
    """;

    public static final String GET_ALL_PATIENTS = """
        SELECT * FROM HO_PATIENT ORDER BY patientId
    """;
}
