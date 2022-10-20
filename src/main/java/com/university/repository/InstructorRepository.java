package com.university.repository;

import org.springframework.stereotype.Repository;

import com.university.model.Instructor;
@Repository
public interface InstructorRepository  extends GenericRepository<Instructor, Long>{

}
