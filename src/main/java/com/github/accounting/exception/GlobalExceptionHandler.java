package com.github.accounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    ResponseEntity<?> serviceExceptionHandler(ServiceException exception) {
        int statusCode = exception.getStatusCode();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(statusCode)
                .message(exception.getMessage())
                .code(exception.getErrorCode())
                .errorType(exception.getErrorType())
                .build();
        return ResponseEntity.status(statusCode != 0 ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<?> runtimeExceptionHandler(RuntimeException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(500)
                .message(exception.getMessage())
                .code(BizErrorCode.OTHER)
                .errorType(ServiceException.ErrorType.Service)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(errorResponse);
    }
}
