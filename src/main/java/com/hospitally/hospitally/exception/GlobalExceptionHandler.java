package com.hospitally.hospitally.exception;

import com.hospitally.hospitally.dto.response.ApiResponse;
import com.hospitally.hospitally.dto.response.ApiResponseBuilder;
import com.hospitally.hospitally.dto.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleAppException(AppException ex) {
        return ResponseEntity.badRequest().body(
                ApiResponseBuilder.error(ex.getMessage(),
                        ErrorResponse.builder().message(ex.getMessage()).build())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleUnhandledExceptions(Exception ex) {
        ex.printStackTrace(); // Optional: helpful for dev/testing. Remove in production or log instead.

        return ResponseEntity.internalServerError().body(
                ApiResponse.builder()
                        .statusCode("99")
                        .statusMessage("Internal Server Error")
                        .data(null)
                        .build()
        );
    }

}
