package com.hospitally.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Patient {

    private int patientId;

    private int patientUserId;

    private String patientDateOfBirth;

    private String patientBloodGroup;

    private String patientGenotype;

    private String patientGender;

    private String patientMaritalStatus;

    private String patientDisabilityStatus;

    private String patientNextOfKin;

    private String patientOccupation;

    private String patientLanguagePreference;

    private String patientCreatedAt;

    private String patientUpdatedAt;

    private String patientStatus;

}
