package com.hospitally.hospitally.repository.database.query;

public class AppointmentQuery {

    public static final String INSERT_APPOINTMENT = """
        INSERT INTO HO_APPOINTMENT (
            appointmentPatientId, appointmentDoctorId,
            appointmentDepartmentId, appointmentPaymentId,
            appointmentDate, appointmentTime,
            appointmentDiagnosis, appointmentStatus,
            appointmentCreatedAt, appointmentUpdatedAt
        ) VALUES (
            :patientId, :doctorId, :departmentId, :paymentId,
            :date, :time, :diagnosis, 'PENDING', GETDATE(), GETDATE()
        )
    """;

    public static final String FIND_APPOINTMENT_BY_ID = """
        SELECT * FROM HO_APPOINTMENT
        WHERE appointmentId = :id AND appointmentStatus != 'CANCELLED'
    """;

    public static final String FIND_ALL_APPOINTMENTS = """
        SELECT * FROM HO_APPOINTMENT
        WHERE appointmentStatus != 'CANCELLED'
    """;

    public static final String UPDATE_APPOINTMENT = """
        UPDATE HO_APPOINTMENT SET
            appointmentDate = COALESCE(:date, appointmentDate),
            appointmentTime = COALESCE(:time, appointmentTime),
            appointmentDiagnosis = COALESCE(:diagnosis, appointmentDiagnosis),
            appointmentStatus = COALESCE(:status, appointmentStatus),
            appointmentUpdatedAt = GETDATE()
        WHERE appointmentId = :id
    """;

    public static final String DELETE_APPOINTMENT = """
        UPDATE HO_APPOINTMENT
        SET appointmentStatus = 'CANCELLED',
            appointmentUpdatedAt = GETDATE()
        WHERE appointmentId = :id
    """;


    public static final String CHECK_PATIENT_EXISTS = """
        SELECT COUNT(*) FROM HO_PATIENT WHERE patientId = :patientId
    """;

    public static final String CHECK_DOCTOR_EXISTS = """
        SELECT COUNT(*) FROM HO_DOCTOR WHERE doctorId = :doctorId
    """;

    public static final String CHECK_DEPARTMENT_EXISTS = """
        SELECT COUNT(*) FROM HO_DEPARTMENT WHERE departmentId = :departmentId
    """;

    public static final String CHECK_PAYMENT_EXISTS = """
        SELECT COUNT(*) FROM HO_PAYMENT WHERE paymentId = :paymentId
    """;
}
