package com.hospitally.hospitally.repository.database.query;

public class AppointmentQuery
{
    public static final String CREATE_APPOINTMENT = """
    INSERT INTO HO_APPOINTMENT (
        appointmentPatientId,
        appointmentDoctorId,
        appointmentDepartmentId,
        appointmentDate,
        appointmentTime
    )
    VALUES (
        :appointmentPatientId,
        :appointmentDoctorId,
        :appointmentDepartmentId,
        :appointmentDate,
        :appointmentTime,
        COALESCE(:appointmentStatus, 'ACTIVE'),
        GETDATE(),
        GETDATE()
    )
""";

    public static final String GET_APPOINTMENT_BY_ID = """
            SELECT * FROM HO_APPOINTMENT WHERE appointmentId = :appointmentId
            """;

    public static final String GET_ALL_APPOINTMENTS = """
            SELECT * FROM HO_APPOINTMENT
            """;

    public static final String UPDATE_APPOINTMENT = """
            UPDATE HO_APPOINTMENT
            SET
            appointmentDiagnosis = COALESCE(:appointmentDiagnosis, appointmentDiagnosis),
            appointmentDate = COALESCE(:appointmentDate, appointmentDate),
            appointmentTime = COALESCE(:appointmentTime, appointmentTime),
            appointmentStatus = COALESCE(:appointmentStatus, appointmentStatus),
            appointmentPaymentId = :appointmentPaymentId,
            appointmentPatientId = :appointmentPatientId,
            appointmentDoctorId = :appointmentDoctorId,
            appointmentDepartmentId = :appointmentDepartmentId,
            appointmentUpdatedAt = GETDATE()
            WHERE appointmentId = :appointmentId
            """;

    public static final String DELETE_APPOINTMENT = """
            UPDATE HO_APPOINTMENT SET appointmentStatus = 'PAID' WHERE appointmentId = :appointmentId
            """;
}
