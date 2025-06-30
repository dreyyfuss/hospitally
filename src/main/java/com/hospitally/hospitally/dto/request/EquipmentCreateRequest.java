package com.hospitally.hospitally.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EquipmentCreateRequest
{
    private int equipmentId;
    private String equipmentName;
    private int equipmentQuantity;
}
