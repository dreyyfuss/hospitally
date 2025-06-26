package com.hospitally.hospitally.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private int userId;

    private String userEmail;

    private String userPhoneNumber;

    private String userFirstName;

    private String userLastName;

    private String userUsername;

    private String userPassword;

    private boolean userIsAdmin;

    private String userCreatedAt;

    private String userUpdatedAt;

    private String userStatus;

}