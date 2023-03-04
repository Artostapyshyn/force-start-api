package com.artostapyshyn.forceStartApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artostapyshyn.forceStartApi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
