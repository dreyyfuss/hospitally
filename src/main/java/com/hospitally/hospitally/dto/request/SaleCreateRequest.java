package com.hospitally.hospitally.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleCreateRequest
{
    private int saleId;

    private int salePaymentId;

    private int salePatientId;
}
