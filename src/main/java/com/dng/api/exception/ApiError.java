package com.dng.api.exception;

import java.time.LocalDateTime;

public class ApiError {

    private LocalDateTime timestamp;
    private String errorCode;
    private String message;
    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(String message, String errorCode) {
        this();
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}