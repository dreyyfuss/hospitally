package com.hospitally.hospitally.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentUpdateRequest
{
    private String departmentName;
    private String departmentStatus;
}
