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
import com.university.model.Instructor;
import com.university.model.Student;
import com.university.services.CourseServices;
import com.university.services.DepartmentServices;
import com.university.services.InstructorServices;
import com.university.services.StudentServices;

@RestController
@RequestMapping(path = "/api")
public class DepartmentController {

	@Autowired
	private final DepartmentServices departmentServices;

	@Autowired
	private final CourseServices courseServices;

	@Autowired
	private final InstructorServices instructorServices;

	@Autowired
	private final StudentServices studentServices;

	public DepartmentController(DepartmentServices departmentServices, CourseServices courseServices,
			InstructorServices instructorServices, StudentServices studentServices) {
		super();
		this.departmentServices = departmentServices;
		this.courseServices = courseServices;
		this.instructorServices = instructorServices;
		this.studentServices = studentServices;
	}

// crud 
	@GetMapping("/departments")
	public ResponseEntity<List<Department>> getAll() {
		try {
			List<Department> lst = departmentServices.findAll();

			if (lst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(lst, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/departments/{id}")
	public ResponseEntity<Department> getById(@PathVariable("id") long id) {
		Department depart = departmentServices.findById(id);
		if (depart != null) {
			return new ResponseEntity<>(depart, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// update
	@PutMapping("/departments/{id}")
	public ResponseEntity<HttpStatus> update(@PathVariable("id") long id, @RequestBody Department data) {
		try {
			boolean result = departmentServices.updateById(data, id);
			if (!result) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// create
	@PostMapping("/departments")
	public ResponseEntity<HttpStatus> create(@RequestBody Department o) {
		try {

			departmentServices.save(o);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// delete
	@DeleteMapping("/departments")
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			departmentServices.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// delete
	@DeleteMapping("/departments/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		try {
			departmentServices.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

/// end of crud
//instructors 
	@GetMapping("/departments/{id}/instructors")
	public ResponseEntity<List<Instructor>> getInstructors() {
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

	@PostMapping("/departments/{id}/add-instructor/{instructor_id}")
	public ResponseEntity<HttpStatus> addInstructor(@PathVariable("id") long id,
			@PathVariable("instructor_id") long instructor_id) {
		try {
			Department depart = departmentServices.findById(id);
			Instructor ins = instructorServices.findById(instructor_id);
			if (depart== null || ins == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			ins.setDepartment(depart);
			instructorServices.save(ins);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/departments/{id}/remove-instructor/{ins_id}")
	public ResponseEntity<HttpStatus> removeInstructors(@PathVariable("id") long id,
			@PathVariable("ins_id") long ins_id) {
		try {
			Instructor st = instructorServices.findById(ins_id);
			if (st == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			st.setDepartment(null);
			instructorServices.save(st);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
/// end of instructors

// courses
	@PostMapping("/departments/{id}/add-course/{course_id}")
	public ResponseEntity<HttpStatus> addCourse(@PathVariable("id") long id,
			@PathVariable("course_id") long course_id) {
		try {
			Department depart = departmentServices.findById(id);
			Course course = courseServices.findById(course_id);
			if (depart == null || course == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			course.setDepartment(depart);
			courseServices.save(course);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

// students
	@GetMapping("/departments/{id}/students")
	public ResponseEntity<List<Student>> getStudents(@PathVariable("id") long id) {
		try {
			List<Student> lst = departmentServices.getStudentByDepartment(id);
			
			if (lst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(lst, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/departments/{id}/add-student/{student_id}")
	public ResponseEntity<HttpStatus> addStudent(@PathVariable("id") long id,
			@PathVariable("student_id") long student_id) {
		try {
			Department depart = departmentServices.findById(id);
			Student st = studentServices.findById(student_id);
			if (depart == null || st == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			st.setDepartment(depart);
			studentServices.save(st);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/departments/{id}/remove-student/{student_id}")
	public ResponseEntity<HttpStatus> removeStudent(@PathVariable("id") long id,
			@PathVariable("student_id") long student_id) {
		try {
			Student st = studentServices.findById(student_id);
			if (st == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			st.setDepartment(null);
			studentServices.save(st);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

///end of students
}
