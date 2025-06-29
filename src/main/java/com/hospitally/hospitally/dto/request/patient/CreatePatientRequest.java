package com.hospitally.hospitally.dto.request.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePatientRequest {
    @NotNull
    private Integer userId;
    
    @NotBlank
    private String gender;
    
    @NotNull
    private LocalDate dateOfBirth;
    
    @NotBlank
    private String bloodGroup;
    
    private String genotype;
    
    private String maritalStatus;
    
    private String disabilityStatus;
    
    @NotBlank
    private String nextOfKinName;
    
    @NotBlank
    private String nextOfKinPhoneNumber;
    
    private String occupation;
    
    private String languagePreference;
}
