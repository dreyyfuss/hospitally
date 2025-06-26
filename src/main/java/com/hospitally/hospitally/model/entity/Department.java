package com.hospitally.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Department {

    private int departmentId;

    private String departmentName;

    private String departmentCreatedAt;

    private String departmentUpdatedAt;

}