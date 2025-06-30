package com.hospitally.hospitally.repository.database.query;

public class PrescriptionQuery {

    public static final String INSERT_PRESCRIPTION = """
        INSERT INTO HO_PRESCRIPTION (
            prescriptionAppointmentId, prescriptionComment,
            prescriptionStatus, prescriptionCreatedAt,
            prescriptionUpdatedAt
        ) VALUES (
            :appointmentId, :comment,
            'ACTIVE', GETDATE(), GETDATE()
        )
    """;

    public static final String FIND_PRESCRIPTION_BY_ID = """
        SELECT prescriptionId,
               prescriptionAppointmentId, prescriptionComment,
               prescriptionStatus, prescriptionCreatedAt,
               prescriptionUpdatedAt
        FROM HO_PRESCRIPTION
        WHERE prescriptionId = :prescriptionId
          AND prescriptionStatus != 'DELETED'
    """;

    public static final String FIND_ALL_PRESCRIPTIONS = """
        SELECT *
        FROM HO_PRESCRIPTION
        WHERE prescriptionStatus != 'DELETED'
    """;

    public static final String UPDATE_PRESCRIPTION = """
        UPDATE HO_PRESCRIPTION
        SET prescriptionComment = COALESCE(:comment, prescriptionComment),
            prescriptionStatus = COALESCE(:status, prescriptionStatus),
            prescriptionUpdatedAt = GETDATE()
        WHERE prescriptionId = :prescriptionId
    """;

    public static final String DELETE_PRESCRIPTION = """
        UPDATE HO_PRESCRIPTION
        SET prescriptionStatus = 'DELETED',
            prescriptionUpdatedAt = GETDATE()
        WHERE prescriptionId = :prescriptionId
    """;

    public static final String CHECK_APPOINTMENT_EXISTS = """
        SELECT COUNT(*)
        FROM HO_APPOINTMENT
        WHERE appointmentId = :appointmentId
          AND appointmentStatus != 'CANCELLED'
    """;
}
