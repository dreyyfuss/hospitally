package com.hospitally.hospitally.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
    private String code;           //"00" - success, "22" - error
    private String statusMessage;  //"Success", "Not Found", "Validation Error"
    private T data;
}
