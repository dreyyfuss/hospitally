package com.hospitally.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Equipment {

    private int equipmentId;

    private int departmentId;

    private String equipmentName;

    private int equipmentQuantity;

    private String equipmentStatus;

    private String equipmentCreatedAt;

    private String equipmentUpdatedAt;

}
