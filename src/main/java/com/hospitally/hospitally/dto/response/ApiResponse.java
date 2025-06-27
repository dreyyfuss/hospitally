package com.hospitally.hospitally.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
    private String statusCode; // "00", "22"
    private String statusMessage; // "Success", "Error"
    private T data;
}
