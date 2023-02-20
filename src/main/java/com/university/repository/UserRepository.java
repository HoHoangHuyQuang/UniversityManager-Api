package com.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.university.model.User;

@Repository
public interface UserRepository extends GenericRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.firstName +' '+ u.lastName LIKE %?1% ORDER BY u.firstName ")
	public List<User> getByName(String name);
}
