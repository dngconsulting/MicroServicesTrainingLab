package com.dng.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {
    private String id;

    private String code;

    private String name;

    private String description;
}
