package com.university.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.university.model.Role;
import com.university.repository.RoleRepository;

@Service
@Transactional
public class RoleServices extends GenericServices<Role, Long> {

	@SuppressWarnings("unused")	
	private final RoleRepository roleRepository;

	
	public RoleServices(RoleRepository roleRepository) {
		super(roleRepository);
		this.roleRepository = roleRepository;
	}
	
	public List<Role> searchByName() {
		List<Role> lst = new ArrayList<Role>();
		
		return lst;
	}
	
}
