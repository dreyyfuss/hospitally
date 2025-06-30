package com.hospitally.hospitally.repository.database.query;

public class SaleQuery
{
    public static final String CREATE_SALE = """
            INSERT INTO HO_SALE (saleId,salePaymentId,salePatientId)
            VALUES(:saleId,:salePaymentId,:salePatientId)
            COALESCE (:saleStatus,'ACTIVE'), GETDATE(),GETDATE()
            """;

    public static final String GET_ALLSALES = """
            SELECT * FROM HO_SALE
            """;

    public static final String GET_SALE_BY_ID = """
            SELECT * FROM HO_SALE WHERE saleId = :saleId
            """;

    public static final String UPDATE_SALE = """
            UPDATE HO_SALE
            SET
            saleStatus = COALESCE(:saleStatus,saleStatus),
            WHERE saleId = :saleId
            """;

    public static final String DELETE_SALE = """
            UPDATE HO_SALE SET saleStatus = 'PAID' WHERE saleId = :saleId
            """;
}
