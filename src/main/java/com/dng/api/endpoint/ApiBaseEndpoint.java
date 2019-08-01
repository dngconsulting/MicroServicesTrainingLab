package com.dng.api.endpoint;

import com.dng.api.domain.Course;
import com.dng.api.dto.CourseDTO;
import com.dng.api.exception.CourseNotFoundException;
import com.dng.api.filter.SpringLoggingFilter;
import com.dng.api.mapper.CourseMapper;
import com.dng.api.repository.CourseRepository;
import com.dng.api.service.CourseService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.List;

@RestController
class ApiBaseEndpoint {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLoggingFilter.class);
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

	@RequestMapping("/")
	public String login(Principal principal) {
		LOGGER.info("Microservice Training Spring Boot OK");
		/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationDetails d = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

		System.out.println("---------------> " + auth.getPrincipal() + " " + SecurityContextHolder.getContext().getAuthentication().getName() + " Token=" + d.getTokenValue() + " " + d.getTokenType());*/
		InetAddress localHost=null;
		try {
			localHost = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "<h1>MicroService Spring Boot OK</h1>" + "Connecté sur la machine " + localHost.getHostAddress() + "<br> OS=" +
				System.getProperty("os.name") + " <br>connectedUser=" + SecurityContextHolder.getContext().getAuthentication() + "<br><a href='swagger-ui.html'>Découvrir l'API </a>";
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