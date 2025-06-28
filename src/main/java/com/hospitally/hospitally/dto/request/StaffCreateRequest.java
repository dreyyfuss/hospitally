package com.hospitally.hospitally.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder

public class StaffCreateRequest
{
    private Integer staffDepartmentId;
    private int staffUserId;
    private String staffRole;
    private String staffGender;
    private Date staffDateOfBirth;
//    private String StaffStatus;

}
