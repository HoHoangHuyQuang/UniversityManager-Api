package com.university.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.university.model.Course;
import com.university.repository.CourseRepository;

@Service
@Transactional
public class CourseServices extends GenericServices<Course,	Long> {

	
	@SuppressWarnings("unused")
	private final CourseRepository courseRepository;
	
	
	public CourseServices(CourseRepository courseRepository) {		
		super(courseRepository);
		this.courseRepository = courseRepository;
	}

	
	
	
}
