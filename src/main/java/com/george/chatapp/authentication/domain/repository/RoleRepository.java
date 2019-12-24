package com.george.chatapp.authentication.domain.repository;

import com.george.chatapp.authentication.domain.model.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	Role findByName(String name);
}
