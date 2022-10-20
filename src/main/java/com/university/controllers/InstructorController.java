package com.university.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.model.Instructor;
import com.university.services.InstructorServices;

@RestController
@RequestMapping(path = "/api")
public class InstructorController {
	@Autowired
	private final InstructorServices instructorServices;


	public InstructorController(InstructorServices instructorServices) {
		super();
		this.instructorServices = instructorServices;
		
	}

	// crud
	@GetMapping("/instructors")
	public ResponseEntity<List<Instructor>> getAll() {
		try {
			List<Instructor> lst = instructorServices.findAll();

			if (lst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(lst, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/instructors/{id}")
	public ResponseEntity<Instructor> getById(@PathVariable("id") long id) {
		Instructor ins = instructorServices.findById(id);
		if (ins != null) {
			return new ResponseEntity<>(ins, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// update
	@PutMapping("/instructors/{id}")
	public ResponseEntity<HttpStatus> update(@PathVariable("id") long id, @RequestBody Instructor data) {
		try {
			boolean result = instructorServices.updateById(data, id);
			if (!result) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// create
	@PostMapping("/instructors")
	public ResponseEntity<HttpStatus> create(@RequestBody Instructor o) {
		try {
			instructorServices.save(o);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// delete
	@DeleteMapping("/instructors")
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			instructorServices.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// delete
	@DeleteMapping("/instructors/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		try {
			instructorServices.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/// end of crud
}
