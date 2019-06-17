package com.dng.api.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String id) {
        super("Le cours " + id + " n'existe pas ");
    }
}
