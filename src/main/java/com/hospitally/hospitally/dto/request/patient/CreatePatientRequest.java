package com.hospitally.hospitally.dto.request.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePatientRequest {
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
}
