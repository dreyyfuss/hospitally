package com.hospitally.hospitally.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class StaffUpdateRequest
{
    private Integer staffDepartmentId;
    private String staffGender;
    private String staffRole;
    private Date staffDateOfBirth;
    private String staffStatus;
}
