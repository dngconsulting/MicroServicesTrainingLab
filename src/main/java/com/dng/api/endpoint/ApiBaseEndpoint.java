package com.dng.api.endpoint;

import com.dng.api.domain.Course;
import com.dng.api.exception.CourseNotFoundException;
import com.dng.api.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class ApiBaseEndpoint {

	@Autowired
	CourseRepository repository;

	public ApiBaseEndpoint(CourseRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/courses")
	@ResponseBody
	List<Course> all() {
		return repository.findAll();
	}

	@PostMapping("/courses")
	Course newCourse(@RequestBody Course newCourse) {
		return repository.save(newCourse);
	}

	@GetMapping("/courses/{id}")
	Course one(@PathVariable String id) {
		return repository.findById(id)
				.orElseThrow(() -> new CourseNotFoundException(id));
	}

	@PutMapping("/courses/{id}")
	Course updateCourse(@RequestBody Course newCourse, @PathVariable String id) {
		return repository.findById(id)
				.map(course -> {
					course.setName(newCourse.getName());
					course.setCode(newCourse.getCode());
					course.setDescription(newCourse.getDescription());
					return repository.save(course);
				})
				.orElseGet(() -> {
					newCourse.setId(id);
					return repository.save(newCourse);
				});
	}

	@DeleteMapping("/courses/{id}")
	void deleteCourse(@PathVariable String id) {
		repository.deleteById(id);
	}
}