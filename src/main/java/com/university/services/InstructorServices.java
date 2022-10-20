package com.university.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.university.model.Instructor;
import com.university.repository.InstructorRepository;

@Service
@Transactional
public class InstructorServices extends GenericServices<Instructor, Long> {
	@SuppressWarnings("unused")
	private final InstructorRepository instructorRepository;

	public InstructorServices(InstructorRepository instructorRepository) {
		super(instructorRepository);
		this.instructorRepository = instructorRepository;
	}


}
