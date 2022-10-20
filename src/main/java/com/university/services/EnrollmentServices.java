package com.university.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.university.model.Enrollment;
import com.university.repository.EnrollmentRepository;
@Service
@Transactional
public class EnrollmentServices extends GenericServices<Enrollment, Long> {

	@SuppressWarnings("unused")
	private final EnrollmentRepository enrollmentRepository;

	public EnrollmentServices(EnrollmentRepository enrollmentRepository) {
		super(enrollmentRepository);
		this.enrollmentRepository = enrollmentRepository;
	}

}
