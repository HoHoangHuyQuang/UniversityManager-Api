package com.university.repository;

import org.springframework.stereotype.Repository;

import com.university.model.Course;
@Repository
public interface CourseRepository  extends GenericRepository<Course, Long>{
	
}
