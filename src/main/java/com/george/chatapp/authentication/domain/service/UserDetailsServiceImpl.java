package com.george.chatapp.authentication.domain.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.george.chatapp.authentication.domain.repository.UserRepository;
import com.george.chatapp.authentication.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findById(username);

        if (!userOptional.isPresent()) {
        	throw new UsernameNotFoundException("User not found");
        } else {
            User user = userOptional.get();
        	Set<SimpleGrantedAuthority> grantedAuthorities = user.getRoles().stream()
        	  .map(role -> new SimpleGrantedAuthority(role.getName()))
        	  .collect(Collectors.toSet());
	
	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        }
    }
}
