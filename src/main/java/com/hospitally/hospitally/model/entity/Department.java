package com.hospitally.hospitally.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department
{

    private int departmentId;

    private String departmentName;

    private String departmentCreatedAt;

    private String departmentStatus;

    private String departmentUpdatedAt;

}