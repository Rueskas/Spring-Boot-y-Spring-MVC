package com.iessanvicente.scondhandmarket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.iessanvicente.scondhandmarket.models.User;
import com.iessanvicente.scondhandmarket.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	@Lazy
	BCryptPasswordEncoder passwordEncoder;	
	
	public User register(User u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return repository.save(u);
	}
	
	public User findById(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public User findByEmail(String email) {
		return repository.findFirstByEmail(email);
	}
}
