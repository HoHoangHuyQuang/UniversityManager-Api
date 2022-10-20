package com.university.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.university.model.Department;
import com.university.model.Instructor;
import com.university.model.Student;
import com.university.repository.DepartmentRepository;

/**
 * @author ADMIN
 *
 */
@Service
@Transactional
public class DepartmentServices extends GenericServices<Department, Long> {

	private final DepartmentRepository departmentRepository;

	public DepartmentServices(DepartmentRepository departmentRepository) {
		super(departmentRepository);
		this.departmentRepository = departmentRepository;
	}

	public List<Instructor> getInstructorsByDepartment(long depart_id) {		
		List<Instructor> lst = departmentRepository.getInstructorsByDepartment(depart_id);
		return lst;
	}
	
	
	public List<Student> getStudentByDepartment(long depart_id){
		List<Student> lst = departmentRepository.getStudentByDepartment(depart_id);
		return lst;
	}
}
