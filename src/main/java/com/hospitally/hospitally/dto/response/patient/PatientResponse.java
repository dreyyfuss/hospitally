package com.hospitally.hospitally.dto.response.patient;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class PatientResponse {
    private Integer patientId;
    private Integer userId;
    private String gender;
    private LocalDate dateOfBirth;
    private String bloodGroup;
    private String genotype;
    private String maritalStatus;
    private String disabilityStatus;
    private String nextOfKinName;
    private String nextOfKinPhoneNumber;
    private String occupation;
    private String languagePreference;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String message;
}
