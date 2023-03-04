package com.artostapyshyn.forceStartApi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artostapyshyn.forceStartApi.entity.User;
import com.artostapyshyn.forceStartApi.repository.UserRepository;
import com.artostapyshyn.forceStartApi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll().stream().toList();
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
}
