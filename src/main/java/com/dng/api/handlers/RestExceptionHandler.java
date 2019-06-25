package com.dng.api.handlers;

import com.dng.api.exception.ApiError;
import com.dng.api.exception.CourseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

//@RestControllerAdvice
public class RestExceptionHandler  {

    //@ExceptionHandler(Exception.class)
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiError handleError (
            Exception ex, WebRequest request) {
        ApiError error = new ApiError(ex.getMessage(),
                ex instanceof CourseNotFoundException?
                        ((CourseNotFoundException) ex).getErrorCode()
                        :"API_ERROR");
        return error;
    }
}