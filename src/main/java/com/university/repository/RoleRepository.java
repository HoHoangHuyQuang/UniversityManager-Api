package com.university.repository;

import org.springframework.stereotype.Repository;

import com.university.model.Role;

@Repository
public interface RoleRepository extends GenericRepository<Role, Long> {

}
