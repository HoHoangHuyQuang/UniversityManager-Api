package com.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.university.model.Enrollment;
import com.university.model.Student;

@Repository
public interface StudentRepository extends GenericRepository<Student, Long>{

	@Query("FROM Enrollment e WHERE e.student.id =?1")
	public List<Enrollment> getEnrolledById(long id);
	
	
	@Query("SELECT st FROM Student st WHERE st.firstName +' '+ st.lastName  LIKE %?1% ORDER BY st.firstName ")
	public List<Student> getByName(String name);
}
