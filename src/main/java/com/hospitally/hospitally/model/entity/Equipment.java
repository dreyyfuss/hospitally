package com.hospitally.hospitally.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    private int equipmentId;

    private int departmentId;

    private String equipmentName;

    private int equipmentQuantity;

    private String equipmentStatus;

    private String equipmentCreatedAt;

    private String equipmentUpdatedAt;

}
