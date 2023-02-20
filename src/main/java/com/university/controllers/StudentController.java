package com.university.controllers;

import java.time.LocalDateTime;
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

import com.university.model.Department;
import com.university.model.Enrollment;
import com.university.model.Section;
import com.university.model.Student;
import com.university.services.DepartmentServices;
import com.university.services.EnrollmentServices;
import com.university.services.SectionServices;
import com.university.services.StudentServices;

@RestController
@RequestMapping(path = "/api")
public class StudentController {
	@Autowired
	private final StudentServices studentServices;

	@Autowired
	private final SectionServices sectionServices;

	@Autowired
	private final EnrollmentServices enrollmentServices;

	@Autowired
	private final DepartmentServices departmentServices;

	public StudentController(StudentServices studentServices, SectionServices sectionServices,
			EnrollmentServices enrollmentServices, DepartmentServices departmentServices) {
		super();
		this.studentServices = studentServices;
		this.sectionServices = sectionServices;
		this.enrollmentServices = enrollmentServices;
		this.departmentServices = departmentServices;
	}

	// crud
	// get
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
		Student st = studentServices.findById(id);
		if (st != null) {
			return new ResponseEntity<>(st, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudent() {
		try {
			List<Student> lst = studentServices.findAll();
			if (lst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(lst, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// update
	@PutMapping("/students/{id}")
	public ResponseEntity<HttpStatus> updateStudent(@PathVariable("id") long id, @RequestBody Student data) {
		try {
			boolean result = studentServices.updateById(data, id);
			if (!result) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// create
	@PostMapping("/students/{dep_id}")
	public ResponseEntity<HttpStatus> createStudent(@RequestBody Student s,
			@PathVariable(required = false) long dep_id) {
		try {
			Department dep = departmentServices.findById(dep_id);
			if (dep != null) {
				s.setDepartment(dep);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			studentServices.save(s);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// delete
	@DeleteMapping("/students/{id}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
		try {
			studentServices.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/students")
	public ResponseEntity<HttpStatus> deleteAllStudent() {
		try {
			studentServices.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
/// end of crud

// enroll 

	@GetMapping("/students/{id}/enrolled")
	public ResponseEntity<List<Enrollment>> getStudentEnrolled(@PathVariable("id") long id) {
		Student st = studentServices.findById(id);
		if (st != null) {
			return new ResponseEntity<>(st.getEnrollments(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/students/{id}/apply/{sec_id}")
	public ResponseEntity<HttpStatus> applyToSection(@PathVariable("id") long id, @PathVariable("sec_id") long sec_id) {
		try {
			Student s = studentServices.findById(id);
			List<Section> lstSec = sectionServices.getActiveSection();
			if (s == null || lstSec == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			for (Section sec : lstSec) {
				if (sec.getSection_iD().equals(sec_id)) {
					Enrollment enroll = new Enrollment(LocalDateTime.now(), null, s, sec);
					enrollmentServices.save(enroll);
					return new ResponseEntity<>(HttpStatus.CREATED);
				}
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/students/{id}/unapply/{sec_id}")
	public ResponseEntity<HttpStatus> removeEnrolled(@PathVariable("id") long id, @PathVariable("sec_id") long sec_id) {
		try {
			Student s = studentServices.findById(id);
			Section sec = sectionServices.findById(sec_id);
			if (s == null || sec == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
			List<Enrollment> enrolls = s.getEnrollments();
			for (Enrollment e : enrolls) {
				if (e.getSection().equals(sec)) {
					enrollmentServices.delete(e);
					return new ResponseEntity<>(HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
/// end of enroll
	
// find 
	@GetMapping("/students/find/{str}")
	public ResponseEntity<List<Student>> getStudentByName(@PathVariable("str") String str) {
		try {
			List<Student> lst = studentServices.getByName(str);
			if (lst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(lst, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
/// end of find

}
