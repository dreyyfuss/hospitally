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
        return error(message, (T) ErrorResponse.builder().message(message).build());
    }

    public static <T> ApiResponse<T> error(String message, T data) {
        return ApiResponse.<T>builder()
                .statusCode("22")
                .statusMessage("Error")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> notFound(String message) {
        return notFound(message, (T) ErrorResponse.builder().message(message).build());
    }

    public static <T> ApiResponse<T> notFound(String message, T data) {
        return ApiResponse.<T>builder()
                .statusCode("22")
                .statusMessage("Not Found")
                .data(data)
                .build();
    }
}
