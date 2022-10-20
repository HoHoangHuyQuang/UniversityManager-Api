package com.university.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.university.model.Enrollment;
import com.university.model.Student;
import com.university.repository.StudentRepository;


@Service
@Transactional
public class StudentServices extends GenericServices<Student, Long> {

	@SuppressWarnings("unused")		
	private final StudentRepository studentRepository;

	public StudentServices(StudentRepository studentRepository) {
		super(studentRepository);
		this.studentRepository = studentRepository;
	}	
	
	public List<Enrollment> getEnrolledByStudentId(long id){
		try {
			List<Enrollment> lst = studentRepository.getEnrolledById(id);
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
