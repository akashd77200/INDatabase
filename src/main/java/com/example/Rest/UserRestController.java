package com.example.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.User;
import com.example.Repo.UserRepo;

@RestController
public class UserRestController {
	
	
	@Autowired
	UserRepo repo;
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@PostMapping("/start")
	public ResponseEntity<?> createuser(@RequestBody User user)
	{
	
		user.setPassword(encoder.encode(user.getPassword()));
		
		
		if(user.getUsername().startsWith("Akash"))
		{
			user.setRole("ADMIN");
			
		}
		else {
			
			user.setRole("USER");
		}
		
		
		User saveuser = repo.save(user);
		
		return new ResponseEntity<>(saveuser,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/hello")
	public String hello()
	{
		
		return "Hello  users";
		
	}
	
	@GetMapping("/user")
	public String user()
	{
		
		
		return "hello users";
		
	}
	
	@GetMapping("/admin")
	public String Admin()
	{
		
		
		return"Hello im admin";
	}
	
	

}
