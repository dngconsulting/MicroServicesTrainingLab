package com.dng.api.endpoint;

import com.dng.api.domain.Course;
import com.dng.api.dto.CourseDTO;
import com.dng.api.exception.CourseNotFoundException;
import com.dng.api.mapper.CourseMapper;
import com.dng.api.repository.CourseRepository;
import com.dng.api.service.CourseService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class ApiBaseEndpoint {

	@Autowired
	CourseRepository repository;

	@Autowired
	CourseService courseService;

	CourseMapper mapper = Mappers.getMapper(CourseMapper.class);;

	public ApiBaseEndpoint(CourseRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/courses")
	@ResponseBody
	List<Course> all() {
		return repository.findAll();
	}

	@GetMapping("/mylogin")
	void login() throws Exception {
		courseService.login("user","userPassdd");
	}

	@GetMapping("/admin")
	@ResponseBody
	String admin() {
		return "Admin connected";
	}

	@PostMapping("/courses")
	CourseDTO newCourse(@RequestBody CourseDTO newCourse) {
		Course course = mapper.toEntity(newCourse);

		Course savedCourse = repository.save(course);
		return mapper.toDTO(savedCourse);
	}

	@GetMapping("/courses/{id}")
	CourseDTO one(@PathVariable String id) {
		return mapper.toDTO(repository.findById(id)
				.orElseThrow(() -> new CourseNotFoundException(id)));
	}

	@PutMapping("/courses/{id}")
	Course updateCourse(@RequestBody CourseDTO newCourse, @PathVariable String id) {
		return repository.findById(id)
				.map(course -> {
					course.setName(newCourse.getName());
					course.setCode(newCourse.getCode());
					course.setDescription(newCourse.getDescription());
					return repository.save(course);
				})
				.orElseGet(() -> {
					newCourse.setId(id);
					return repository.save(mapper.toEntity(newCourse));
				});
	}

	@DeleteMapping("/courses/{id}")
	void deleteCourse(@PathVariable String id) {
		repository.deleteById(id);
	}

}