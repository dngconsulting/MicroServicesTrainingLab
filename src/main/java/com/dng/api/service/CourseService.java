package com.dng.api.service;

import com.dng.api.domain.Course;
import com.dng.api.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired CourseRepository courseRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    public List<Course> findByExample(String description) {

        Course course = new Course();
        course.setDescription(description);
        // Possible d'utiliser des matchers
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIncludeNullValues();

        Example<Course> example = Example.of(course);
        List<Course> all = courseRepository.findAll(example);

        return all;
    }

    public void login(String username, String password) {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority("USER"));
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (authenticate.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            System.out.println("Utilisateur authentifié");
        }
    }
}
