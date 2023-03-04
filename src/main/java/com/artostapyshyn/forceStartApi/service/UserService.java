package com.artostapyshyn.forceStartApi.service;

import java.util.List;
import java.util.Optional;

import com.artostapyshyn.forceStartApi.entity.User;

public interface UserService {
	Optional<User> findById(Long id);
	
	User findByUsername(String username);
	
	User save(User user);

	List<User> findAll();
	
	void deleteById(Long id);
}