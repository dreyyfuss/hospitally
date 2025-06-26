package com.hospitally.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Medication {

    private int medicationId;

    private String medicationName;

    private String medicationDescription;

    private double medicationStock;

    private double medicationPrice;

    private String medicationCreatedAt;

    private String medicationUpdatedAt;

    private String medicationStatus;

}
