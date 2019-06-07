package com.dng.api.endpoint;

import java.util.List;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dng.api.service.ApiBaseService;
import com.dng.api.service.RegistryService;
import com.dng.api.domain.Registry;

import com.dng.api.domain.Person;
import com.dng.api.service.PersonService;

@RestController
public class ApiBaseEndpoint {
	
	@Autowired
	private ApiBaseService service;

	@Autowired
	private PersonService personService;

	@Autowired
	private RegistryService jpaService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> helloWorld() {
		return new ResponseEntity<>(service.hello(), HttpStatus.OK);
	}

	@GetMapping(path = "/date/now")
	@ResponseBody
	public String localDateNow() {
		return service.localDate();
	}

	@GetMapping(path = "/registry/list-all")
	@ResponseBody
	public List<Registry> listAllRegistries() {
		return jpaService.findAll();
	}

	@PostMapping(path = "/registries/save")
	@ResponseBody
	public Registry saveRegistry(@PathParam("description") String description) {
		Registry reg = jpaService.save(description);
		return reg;
	}

	@PutMapping(path = "/registries/update/{id}")
	@ResponseBody
	public Registry updateRegistry(@PathVariable("id") Long id, @PathParam("description") String description) {
		Registry reg = jpaService.update(id, description);
		return reg;
	}

	@DeleteMapping(path = "registries/remove/{id}")
	@ResponseBody
	public String deleteRegistry(@PathVariable("id") Long id) {
		jpaService.delete(id);
		return "Registry deleted";
	}

	@GetMapping(path = "/persons/surname/{surname}")
	@ResponseBody
	public List<Person> listPersonsBySurname(@PathVariable("surname") String surname) {
		return personService.findBySurname(surname);
	}

	@PostMapping(path = "/persons/new")
	@ResponseBody
	public Person savePerson(@RequestBody Person person) {
		return personService.save(person);
	}

	@PutMapping(path = "/persons/update")
	@ResponseBody
	public Person updateRegistry(@RequestBody Person person) {
		return personService.save(person);
	}

	@DeleteMapping(path = "persons/remove/{id}")
	@ResponseBody
	public String deleteRegistry(@PathVariable("id") String id) {
		personService.delete(id);
		return "Person removed";
	}

}
