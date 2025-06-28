package com.hospitally.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    private int departmentId;

    private String departmentName;

    private String departmentCreatedAt;

    private String departmentUpdatedAt;

}