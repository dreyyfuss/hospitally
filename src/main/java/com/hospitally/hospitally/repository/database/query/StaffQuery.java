package com.hospitally.hospitally.repository.database.query;

public class StaffQuery

{
    public static final String CREATE_STAFF = """
            INSERT INTO HO_STAFF (staffDepartmentId,staffUserId,staffRole,staffGender,staffDateOfBirth,staffStatus,staffCreatedAt,staffUpdatedAt)
            VALUES(:staffDepartmentId,:staffUserId,:staffRole,:staffGender,:staffDateOfBirth,
            COALESCE(:StaffStatus,'ACTIVE'), GETDATE(),GETDATE())
            """;

    public static final String GET_ALLSTAFF = """
            SELECT * FROM HO_STAFF
            """;

    public static final String GET_STAFF_BY_ID = """
            SELECT * FROM HO_STAFF WHERE staffUserId = :staffUserId
            """;

    public static final String UPDATE_STAFF = """
            UPDATE HO_STAFF
            SET staffDepartmentId = COALESCE(:staffDepartmentId,staffDepartmentId),
            staffRole = COALESCE(:staffRole,staffRole),
            staffGender = COALESCE(:staffGender,staffGender),
            staffDateOfBirth = COALESCE(:staffDateOfBirth,staffDateOfBirth),
            staffStatus = COALESCE(:staffStatus,staffStatus),
            staffUpdatedAt = GETDATE()
            WHERE staffId = :staffId
            """;

    public static final String DELETE_STAFF = """
            UPDATE HO_STAFF SET staffStatus = 'INACTIVE' WHERE staffId = :staffId
            """;
}
