package com.hospitally.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Doctor {

    private int doctorId;

    private int doctorStaffId;

    private String doctorSpecialization;

    private String doctorCreatedAt;

    private String doctorUpdatedAt;

    private String doctorStatus;

}
