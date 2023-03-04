package com.artostapyshyn.forceStartApi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	 
	  @Autowired
	    private UserDetailsService userDetailsService;

	    @Bean
	    public static PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }

	    @Bean	
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    	http
	    		.authorizeHttpRequests()
	    		.requestMatchers(HttpMethod.POST, "/login").permitAll()
	    		.requestMatchers(HttpMethod.POST, "/register").permitAll() 
	    		.anyRequest()
	            .permitAll()
	            .and()
	            .httpBasic()
	            .and()
	            .csrf()
	            .disable();

			return http.build();
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	        	.userDetailsService(userDetailsService)
	        	.passwordEncoder(passwordEncoder());
	    }
	    
	    @Bean
	    public AuthenticationManager authenticationManager(
	                                 AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }

}