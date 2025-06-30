package com.hospitally.hospitally.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    private Integer doctorId;
    private Integer doctorStaffId;
    private String doctorSpecialization;
    private String doctorStatus;
    private LocalDateTime doctorCreatedAt;
    private LocalDateTime doctorUpdatedAt;
}
