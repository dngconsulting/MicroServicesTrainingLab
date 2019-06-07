package com.dng.api.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dng.api.domain.Course;
import com.dng.api.service.CourseService;

@RestController
public class ApiBaseEndpoint {

	@Autowired
	private CourseService courseService;

	@GetMapping(path = "/courses/{code}")
	@ResponseBody
	public Course listByCode(@PathVariable("code") String code) {
		return courseService.findByCode(code);
	}

	@GetMapping(path = "/courses/")
	@ResponseBody
	public List<Course> listAllCourses() {
		return courseService.findAll();
	}

	@PostMapping(path = "/courses/new")
	@ResponseBody
	public Course saveCourse(@RequestBody Course Course) {
		return courseService.save(Course);
	}

	@PutMapping(path = "/courses/update")
	@ResponseBody
	public Course updateCourse(@RequestBody Course Course) {
		return courseService.save(Course);
	}

	@DeleteMapping(path = "/courses/remove/{id}")
	@ResponseBody
	public String deleteCourse(@PathVariable("id") String id) {
		courseService.delete(id);
		return "Course removed";
	}

}
