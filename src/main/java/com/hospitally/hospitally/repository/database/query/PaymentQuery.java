package com.hospitally.hospitally.repository.database.query;

public class PaymentQuery {
    public static final String INSERT_PAYMENT = """
        INSERT INTO HO_PAYMENT (
            paymentTime, paymentReference, paymentAmount, paymentStatus, paymentCreatedAt, paymentUpdatedAt
        ) VALUES (
            :time, :reference, :amount, 'PENDING', GETDATE(), GETDATE()
        )
    """;

    public static final String FIND_PAYMENT_BY_ID = """
        SELECT * FROM HO_PAYMENT WHERE paymentId = :id
    """;

    public static final String FIND_ALL_PAYMENTS = """
        SELECT * FROM HO_PAYMENT
    """;

    public static final String UPDATE_PAYMENT = """
        UPDATE HO_PAYMENT SET
            paymentAmount = COALESCE(:amount, paymentAmount),
            paymentStatus = COALESCE(:status, paymentStatus),
            paymentUpdatedAt = GETDATE()
        WHERE paymentId = :id
    """;

    public static final String DELETE_PAYMENT = """
    UPDATE HO_PAYMENT
    SET paymentStatus = 'DELETED', paymentUpdatedAt = GETDATE()
    WHERE paymentId = :id
""";

    public static final String CHECK_PAYMENT_EXISTS = """
    SELECT COUNT(*) FROM HO_PAYMENT WHERE paymentId = :id AND paymentStatus != 'DELETED'
""";


    public static final String CHECK_REFERENCE_EXISTS = """
        SELECT COUNT(*) FROM HO_PAYMENT WHERE paymentReference = :reference
    """;
}
