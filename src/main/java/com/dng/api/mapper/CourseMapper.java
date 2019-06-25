package com.dng.api.mapper;

import com.dng.api.domain.Course;
import com.dng.api.dto.CourseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseDTO source);
    CourseDTO toDTO(Course destination);
}
