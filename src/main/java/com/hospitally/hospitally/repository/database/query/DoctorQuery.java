package com.hospitally.hospitally.repository.database.query;

public class DoctorQuery {
    public static final String INSERT_DOCTOR = """
            INSERT INTO HO_DOCTOR (
            doctorStaffId, doctorSpecialization, doctorStatus,
            doctorCreatedAt, doctorUpdatedAt
            ) VALUES (
            :staffId, :specialization, 'ACTIVE', GETDATE(),GETDATE()
            )
    """;

    public static final String FIND_DOCTOR_BY_ID = """
        SELECT doctorId, doctorStaffId, doctorSpecialization, doctorStatus, doctorCreatedAt, doctorUpdatedAt
        FROM HO_DOCTOR
        WHERE doctorId = :doctorId AND doctorStatus = 'ACTIVE'
    """;

    public static final String FIND_ALL_DOCTOR = """
            SELECT * FROM HO_DOCTOR WHERE doctorStatus != 'DELETED'
    """;

    public static final String UPDATE_DOCTOR = """
        UPDATE HO_DOCTOR
        SET
            doctorSpecialization = COALESCE(:specialization, doctorSpecialization),
            doctorStatus = COALESCE(:status, doctorStatus),
            doctorUpdatedAt = GETDATE()
        WHERE doctorId = :doctorId
    """;

    public static final String DELETE_DOCTOR = """
        UPDATE HO_DOCTOR
        SET doctorStatus = 'DELETED',
            doctorUpdatedAt = GETDATE()
        WHERE doctorId = :doctorId
    """;

    public static final String CHECK_STAFF_EXISTS = """
        SELECT COUNT(*) FROM HO_STAFF WHERE staffId = :staffId
    """;

    public static final String CHECK_DOCTOR_EXISTS = """
         SELECT COUNT(*) FROM HO_DOCTOR 
         WHERE doctorStaffId = :staffId AND doctorStatus != 'DELETED'
    """;
}