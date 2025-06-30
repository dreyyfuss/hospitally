package com.hospitally.hospitally.dto.request.doctor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDoctorRequest {
    private Integer staffId;
    private String specialization;
}
