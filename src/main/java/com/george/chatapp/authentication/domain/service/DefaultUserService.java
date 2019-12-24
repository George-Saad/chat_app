package com.george.chatapp.authentication.domain.service;

import java.util.Arrays;

import com.george.chatapp.authentication.domain.repository.RoleRepository;
import com.george.chatapp.authentication.domain.repository.UserRepository;
import com.george.chatapp.authentication.domain.model.Role;
import com.george.chatapp.authentication.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DefaultUserService implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	@Transactional
	public User createUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByName("ROLE_USER");
		user.addRoles(Arrays.asList(userRole));
		return userRepository.save(user);
	}
}
