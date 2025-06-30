package com.hospitally.hospitally.repository.database.query;

public class EquipmentQuery
{
    public static final String CREATE_EQUIPMENT = """
            INSERT INTO HO_EQUIPMENT (equipmentId,equipmentName,equipmentQuantity,equipmentCreatedAt,equipmentUpdatedAt)
            VALUES(:equipmentId,:equipmentName,:equipmentQuantity,
            COALESCE (:departmentStatus, GETDATE()),GETDATE()
            """;

    public static final String GET_ALLEQUIPMENTS = """
            SELECT * FROM HO_EQUIPMENT
            """;

    public static final String GET_DEPARTMENT_BY_ID = """
            SELECT * FROM HO_EQUIPMENT WHERE equipmentId = :equipmentId
            """;

    public static final String UPDATE_EQUIPMENT = """
            UPDATE HO_EQUIPMENT
            SET
            equipmentStatus = COALESCE(:equipmentStatus,equipmentStatus),
            equipmentUpdatedAt = GETDATE()
            WHERE equipmentId = :equipmentId
            """;

    public static final String DELETE_EQUIPMENT = """
            UPDATE HO_EQUIPMENT SET equipmentStatus = 'UNAVAILABLE' WHERE equipmentId = :equipmentId
            """;
}
