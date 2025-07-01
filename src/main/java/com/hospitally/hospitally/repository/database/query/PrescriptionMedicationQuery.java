package com.hospitally.hospitally.repository.database.query;

public class PrescriptionMedicationQuery {

    public static final String INSERT_PRESCRIPTION_MEDICATION = """
        INSERT INTO HO_PRESCRIPTION_MEDICATION (
            prescriptionMedicationMedicationId, prescriptionMedicationPrescriptionId, prescriptionMedicationQuantity, prescriptionMedicationStatus, prescriptionMedicationCreatedAt, prescriptionMedicationUpdatedAt
        ) VALUES (
            :medicationId, :prescriptionId, :quantity, 'ACTIVE', GETDATE(), GETDATE()
        )
    """;

    public static final String FIND_PRESCRIPTION_MEDICATION_BY_ID = """
        SELECT medicationPrescriptionId, prescriptionMedicationMedicationId, prescriptionMedicationPrescriptionId, prescriptionMedicationQuantity, prescriptionMedicationStatus, prescriptionMedicationCreatedAt, prescriptionMedicationUpdatedAt
        FROM HO_PRESCRIPTION_MEDICATION
        WHERE medicationPrescriptionId = :id AND prescriptionMedicationStatus != 'DISCONTINUED'
    """;

    public static final String FIND_ALL_PRESCRIPTION_MEDICATIONS = """
        SELECT medicationPrescriptionId, prescriptionMedicationMedicationId, prescriptionMedicationPrescriptionId, prescriptionMedicationQuantity, prescriptionMedicationStatus, prescriptionMedicationCreatedAt, prescriptionMedicationUpdatedAt
        FROM HO_PRESCRIPTION_MEDICATION
        WHERE prescriptionMedicationStatus != 'DISCONTINUED'
    """;

    public static final String UPDATE_PRESCRIPTION_MEDICATION = """
        UPDATE HO_PRESCRIPTION_MEDICATION
        SET
            prescriptionMedicationQuantity = COALESCE(:quantity, prescriptionMedicationQuantity),
            prescriptionMedicationStatus = COALESCE(:status, prescriptionMedicationStatus),
            prescriptionMedicationUpdatedAt = GETDATE()
        WHERE medicationPrescriptionId = :id
    """;

    public static final String DELETE_PRESCRIPTION_MEDICATION = """
        UPDATE HO_PRESCRIPTION_MEDICATION
        SET prescriptionMedicationStatus = 'DISCONTINUED',
            prescriptionMedicationUpdatedAt = GETDATE()
        WHERE medicationPrescriptionId = :id
    """;


    public static final String CHECK_MEDICATION_EXISTS = """
        SELECT COUNT(*) FROM HO_MEDICATION WHERE medicationId = :medicationId AND medicationStatus = 'AVAILABLE'
    """;

    public static final String CHECK_PRESCRIPTION_EXISTS = """
        SELECT COUNT(*) FROM HO_PRESCRIPTION WHERE prescriptionId = :prescriptionId AND prescriptionStatus = 'ACTIVE'
    """;

    public static final String CHECK_PRESCRIPTION_MEDICATION_EXISTS = """
        SELECT COUNT(*) FROM HO_PRESCRIPTION_MEDICATION WHERE medicationPrescriptionId = :id AND prescriptionMedicationStatus != 'DISCONTINUED'
    """;
}
