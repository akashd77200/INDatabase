package com.example.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.User;
import java.util.List;
import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);

}
