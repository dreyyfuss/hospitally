package com.hospitally.hospitally.repository.database.query;

public class DepartmentQuery
{
    public static final String CREATE_DEPARTMENT= """
        INSERT INTO HO_DEPARTMENT (departmentId,departmentName,departmentCreatedAt,departmentUpdatedAt)
        VALUES(:departmentId,:departmentName,
        COALESCE (:departmentStatus,GETDATE()),GETDATE()
        """;

    public static final String GET_ALLDEPARTMENT = """
            SELECT * FROM HO_DEPARTMENT
            """;

    public static final String GET_DEPARTMENT_BY_ID = """
            SELECT * FROM HO_DEPARTMENT WHERE departmentId = :departmentId
            """;

    public static final String UPDATE_DEPARTMENT = """
            UPDATE HO_DEPARTMENT
            SET
            departmentName = COALESCE(:departmentName,departmentName),
            departmentStatus = COALESCE(:departmentStatus,departmentStatus),
            departmentUpdatedAt = GETDATE()
            WHERE departmentId = :departmentId
            """;

    public static final String DELETE_DEPARTMENT ="""
        UPDATE HO_DEPARTMENT SET departmentStatus = 'INACTIVE' WHERE departmentId = :departmentId
        """ ;
}
