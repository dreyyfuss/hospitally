package com.hospitally.hospitally.repository.database.query;

public class MedicationSaleQuery {
    public static final String INSERT_MEDICATION_SALE = """
        INSERT INTO HO_MEDICATION_SALE (
            medicationSaleSaleId, medicationSaleMedicationId, medicationSaleQuantity,
            medicationSaleStatus, medicationSaleCreatedAt, medicationSaleUpdatedAt
        ) VALUES (
            :saleId, :medicationId, :quantity, 'ACTIVE', GETDATE(), GETDATE()
        )
    """;

    public static final String FIND_MEDICATION_SALE_BY_ID = """
        SELECT medicationSaleId, medicationSaleSaleId, medicationSaleMedicationId, medicationSaleQuantity,
               medicationSaleStatus, medicationSaleCreatedAt, medicationSaleUpdatedAt
        FROM HO_MEDICATION_SALE
        WHERE medicationSaleId = :medicationSaleId AND medicationSaleStatus = 'ACTIVE'
    """;

    public static final String FIND_ALL_MEDICATION_SALES = """
        SELECT * FROM HO_MEDICATION_SALE WHERE medicationSaleStatus != 'CANCELLED'
    """;

    public static final String UPDATE_MEDICATION_SALE = """
        UPDATE HO_MEDICATION_SALE
        SET
            medicationSaleQuantity = COALESCE(:quantity, medicationSaleQuantity),
            medicationSaleStatus = COALESCE(:status, medicationSaleStatus),
            medicationSaleUpdatedAt = GETDATE()
        WHERE medicationSaleId = :medicationSaleId
    """;

    public static final String DELETE_MEDICATION_SALE = """
        UPDATE HO_MEDICATION_SALE
        SET medicationSaleStatus = 'CANCELLED',
            medicationSaleUpdatedAt = GETDATE()
        WHERE medicationSaleId = :medicationSaleId
    """;

    public static final String CHECK_SALE_EXISTS = """
        SELECT COUNT(*) FROM HO_SALE WHERE saleId = :saleId
    """;

    public static final String CHECK_MEDICATION_EXISTS = """
        SELECT COUNT(*) FROM HO_MEDICATION WHERE medicationId = :medicationId
    """;
}
