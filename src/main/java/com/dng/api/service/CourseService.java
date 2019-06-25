package com.dng.api.service;

import com.dng.api.domain.Course;
import com.dng.api.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired CourseRepository courseRepository;

    public List<Course> findByExample() {

        Course course = new Course();
        course.setName("mon cours");
        // Possible d'utiliser des matchers
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIncludeNullValues();

        Example<Course> example = Example.of(course);
        List<Course> all = courseRepository.findAll(example);

        return all;
    }
}
