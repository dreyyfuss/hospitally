package com.hospitally.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class Patient {
    private Integer patientId;
    private Integer patientUserId;
    private String patientGender;
    private LocalDate patientDateOfBirth;
    private String patientBloodGroup;
    private String patientGenotype;
    private String patientMaritalStatus;
    private String patientDisabilityStatus;
    private String patientNextOfKinName;
    private String patientNextOfKinPhoneNumber;
    private String patientOccupation;
    private String patientLanguagePreference;
    private LocalDateTime patientCreatedAt;
    private LocalDateTime patientUpdatedAt;
    private String patientStatus;

}
