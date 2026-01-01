package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Entity.User;
import com.example.Repo.UserRepo;

@Service
public class CustomUSerDetailService implements UserDetailsService {

	
	@Autowired
	UserRepo repo;
	
	//BCryptPasswordEncoder encoder()
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> byUsername = repo.findByUsername(username);
		
		if(byUsername .isPresent())
		{
			
			User user = byUsername.get();
			
			
			return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
					.password(user.getPassword())
					.roles(user.getRole())
					.build();
			
		}
		else {
			
			
			throw  new UsernameNotFoundException("userNotFoundEXception");
			
			
			
		}
		
		
		
		
	}

}
