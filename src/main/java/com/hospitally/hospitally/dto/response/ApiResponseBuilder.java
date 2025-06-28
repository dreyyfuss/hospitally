package com.hospitally.hospitally.dto.response;

public class ApiResponseBuilder {

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .statusCode("00")
                .statusMessage(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .statusCode("22")
                .statusMessage("Error")
                .data(null)
                .build();
    }

    public static <T> ApiResponse<T> notFound(String message) {
        return ApiResponse.<T>builder()
                .statusCode("22")
                .statusMessage("Not Found")
                .data(null)
                .build();
    }
}
