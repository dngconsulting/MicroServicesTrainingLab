package com.dng.api.service;

import com.dng.api.domain.Course;
import com.dng.api.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

	@Autowired
	private CourseRepository coursesRepo;

	public Course findByCode(String code) {
		return coursesRepo.findByCode(code);
	}

	public List<Course> findAll() {
		return coursesRepo.findAll();
	}

	public Course save(Course person) {
		return coursesRepo.save(person);
	}

	public void delete(String id) {
		Optional<Course> person = coursesRepo.findById(id);
		if (person.isPresent()) {
			coursesRepo.delete(person.get());
		}
	}

}
