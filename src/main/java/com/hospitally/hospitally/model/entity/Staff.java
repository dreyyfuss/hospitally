package com.hospitally.hospitally.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    private int staffId;

    private int staffUserId;

    private int staffDepartmentId;

    private String staffRole;

    private String staffGender;

    private Date staffDateOfBirth;

    private String staffJoinDate;

    private String staffStatus;

    private String staffCreatedAt;

    private String staffUpdatedAt;

}
