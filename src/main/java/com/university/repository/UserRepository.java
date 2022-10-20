package com.university.repository;

import org.springframework.stereotype.Repository;

import com.university.model.User;

@Repository
public interface UserRepository extends GenericRepository<User, Long> {

}
