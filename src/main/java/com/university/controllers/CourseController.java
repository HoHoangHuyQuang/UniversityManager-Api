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

import com.university.model.Course;
import com.university.model.Department;
import com.university.services.CourseServices;
import com.university.services.DepartmentServices;

@RestController
@RequestMapping(path = "/api")
public class CourseController {

	@Autowired
	private final CourseServices courseServices;

	@Autowired
	private final DepartmentServices departmentServices;

	public CourseController(CourseServices courseServices, DepartmentServices departmentServices) {
		super();
		this.courseServices = courseServices;
		this.departmentServices = departmentServices;
	}

	// crud
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getAll() {
		try {
			List<Course> lst = courseServices.findAll();

			if (lst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(lst, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/courses/{id}")
	public ResponseEntity<Course> getById(@PathVariable("id") long id) {
		Course result = courseServices.findById(id);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// update
	@PutMapping("/Courses/{id}")
	public ResponseEntity<HttpStatus> update(@PathVariable("id") long id, @RequestBody Course data) {
		try {
			boolean result = courseServices.updateById(data, id);
			if (!result) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// create
	@PostMapping("/courses/{dep_id}")
	public ResponseEntity<HttpStatus> create(@RequestBody Course o, @PathVariable(required = false) long dep_id) {
		try {
			Department dep = departmentServices.findById(dep_id);
			if (dep != null) {
				o.setDepartment(dep);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			courseServices.save(o);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// delete
	@DeleteMapping("/courses")
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			courseServices.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// delete
	@DeleteMapping("/courses/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		try {
			courseServices.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/// end of crud
}
