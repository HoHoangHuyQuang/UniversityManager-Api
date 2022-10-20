package com.university.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.university.model.User;
import com.university.repository.UserRepository;

@Service
@Transactional
public class UserServices extends GenericServices<User, Long>{

	@SuppressWarnings("unused")
	private final UserRepository userRepository;

	public UserServices(UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;
	};
	
	
}
