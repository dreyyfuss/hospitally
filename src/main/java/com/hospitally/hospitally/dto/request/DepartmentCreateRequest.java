package com.hospitally.hospitally.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentCreateRequest
{
    private int departmentId;

    private String departmentName;
}
