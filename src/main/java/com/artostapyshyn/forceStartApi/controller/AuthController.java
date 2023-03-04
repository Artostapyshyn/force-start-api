package com.artostapyshyn.forceStartApi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artostapyshyn.forceStartApi.entity.User;
import com.artostapyshyn.forceStartApi.enums.Role;
import com.artostapyshyn.forceStartApi.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class AuthController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<List<Object>> authenticateUser(@RequestBody User user) throws AuthenticationException{
    	List<Object> response = new ArrayList<>();
    	
    	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
    			user.getUsername(), user.getPassword()));
    	SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Seller signed-in.");
        response.add("success:true");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	 
	@PostMapping("/register")
    ResponseEntity<List<Object>> signUpSeller(@Valid @RequestBody User user) {
        List<Object> response = new ArrayList<>();
		List<User> users = userService.findAll();
        for (User u : users) {
            
        	if (u.equals(user)) {
                log.warn("Credentials already exists!");
                response.add("Seller already exists!");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }
        }
        
        log.info("Registered seller: " + user.toString());
         
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        user.setToken(uuidAsString);
        userService.save(user);
        response.add("You're successfully registered!");
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
