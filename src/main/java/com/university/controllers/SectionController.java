package com.university.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
import com.university.model.Enrollment;
import com.university.model.Instructor;
import com.university.model.Section;
import com.university.model.Student;
import com.university.services.CourseServices;
import com.university.services.EnrollmentServices;
import com.university.services.InstructorServices;
import com.university.services.SectionServices;
import com.university.services.StudentServices;

@RestController
@RequestMapping(path = "/api")
public class SectionController {

	@Autowired
	private final InstructorServices instructorServices;

	@Autowired
	private final SectionServices sectionServices;

	@Autowired
	private final CourseServices courseServices;

	@Autowired
	private final EnrollmentServices enrollmentServices;
	
	@Autowired
	private final StudentServices studentServices;

	

	public SectionController(InstructorServices instructorServices, SectionServices sectionServices,
			CourseServices courseServices, EnrollmentServices enrollmentServices, StudentServices studentServices) {
		super();
		this.instructorServices = instructorServices;
		this.sectionServices = sectionServices;
		this.courseServices = courseServices;
		this.enrollmentServices = enrollmentServices;
		this.studentServices = studentServices;
	}

	// crud
	@GetMapping("/sections")
	public ResponseEntity<List<Section>> getAll() {
		try {
			List<Section> lst = sectionServices.findAll();

			if (lst.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(lst, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/sections/{id}")
	public ResponseEntity<Section> getById(@PathVariable("id") long id) {
		Section result = sectionServices.findById(id);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// update
	@PutMapping("/sections/{id}")
	public ResponseEntity<HttpStatus> update(@PathVariable("id") long id, @RequestBody Section data) {
		try {
			boolean result = sectionServices.updateById(data, id);
			if (!result) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// create
	@PostMapping("/sections/{course_id}")
	public ResponseEntity<HttpStatus> create(@RequestBody Section o, @PathVariable(required = true) long course_id) {
		try {
			Course course = courseServices.findById(course_id);
			if (course == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			o.setCourse(course);
			sectionServices.save(o);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// delete
	@DeleteMapping("/sections")
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			sectionServices.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// delete
	@DeleteMapping("/sections/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		try {
			sectionServices.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/// end of crud
// instructors
	@GetMapping("/sections/{id}/instructors")
	public ResponseEntity<List<Instructor>> getInstructors(@PathVariable("id") long id) {
		Section result = sectionServices.findById(id);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(result.getInstructor(), HttpStatus.OK);
	}

	@PostMapping("/sections/{id}/add-instructor/{ins_id}")
	public ResponseEntity<HttpStatus> addInstructor(@PathVariable("id") long id, @PathVariable("ins_id") long ins_id) {
		try {
			Section sec = sectionServices.findById(id);
			Instructor ins = instructorServices.findById(ins_id);
			if (ins == null || sec == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			sec.addInstrutor(ins);
			sectionServices.save(sec);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/sections/{id}/remove-instructor/{ins_id}")
	public ResponseEntity<HttpStatus> removeInstructor(@PathVariable("id") long id,
			@PathVariable("ins_id") long ins_id) {
		try {
			Section sec = sectionServices.findById(id);
			Instructor ins = instructorServices.findById(ins_id);
			if (ins == null || sec == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			sec.getInstructor().remove(ins);
			sectionServices.save(sec);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

/// end of instructors	
	
// students
	@GetMapping("/sections/{id}/students")
	public ResponseEntity<List<Student>> getAssignStudents(@PathVariable("id") long id) {
		Section result = sectionServices.findById(id);
		if (result == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Enrollment> lstEnroll= result.getEnrollments();
		List<Student> lstStudent = lstEnroll.stream().map(Enrollment::getStudent).collect(Collectors.toList());		
		
		return new ResponseEntity<>(lstStudent, HttpStatus.OK);
	}

	@PostMapping("/sections/{id}/add-student/{stu_id}")
	public ResponseEntity<HttpStatus> addStudent(@PathVariable("id") long id, @PathVariable("stu_id") long stu_id) {
		try {
			Section sec = sectionServices.findById(id);
			Student stu = studentServices.findById(stu_id);
			if (sec == null || stu == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Enrollment enroll = new Enrollment(LocalDateTime.now(), null, stu, sec);
			enrollmentServices.save(enroll);			
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/sections/{id}/remove-student/{stu_id}")
	public ResponseEntity<HttpStatus> removeStudent(@PathVariable("id") long id,
			@PathVariable("stu_id") long stu_id) {
		try {
			Student s = studentServices.findById(stu_id);
			Section sec = sectionServices.findById(id);
			if(s== null || sec == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}			
			List<Enrollment> enrolls = s.getEnrollments();
			for (Enrollment e : enrolls) {
				if(e.getSection().equals(sec)) {
					enrollmentServices.delete(e);
					return new ResponseEntity<>(HttpStatus.OK);
				}
			}	
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
/// end of students

}
