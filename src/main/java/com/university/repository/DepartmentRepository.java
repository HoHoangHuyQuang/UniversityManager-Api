package com.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.university.model.Department;
import com.university.model.Instructor;
import com.university.model.Student;

@Repository
public interface DepartmentRepository extends GenericRepository<Department, Long> {

	@Query("SELECT i FROM Instructor i WHERE i.department.department_iD = ?1 ")
	public List<Instructor> getInstructorsByDepartment(long depart_id);
	
	@Query("SELECT s FROM Student s WHERE s.department.department_iD = ?1 ")
	public List<Student> getStudentByDepartment(long depart_id);
}
