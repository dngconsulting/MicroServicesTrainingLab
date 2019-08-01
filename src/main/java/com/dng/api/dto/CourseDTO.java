package com.dng.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class CourseDTO {
    private String id;

    private String code;

    private String name;

    private String description;

    public CourseDTO(String id, String code, String name, String description) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public CourseDTO() {}

}
