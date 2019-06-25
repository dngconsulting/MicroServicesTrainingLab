package com.dng.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CourseNotFoundException extends RuntimeException {

    private String errorCode = "COURSE_NOT_FOUND" ;

    public CourseNotFoundException(String id) {
        super("Le cours " + id + " n'existe pas ");
    }
    public String getErrorCode() {
        return errorCode;
    }

}
