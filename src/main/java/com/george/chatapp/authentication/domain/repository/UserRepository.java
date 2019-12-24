package com.george.chatapp.authentication.domain.repository;


import com.george.chatapp.authentication.domain.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String> {

}
