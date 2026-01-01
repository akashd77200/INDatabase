package com.example.Sequrity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.example.service.CustomUSerDetailService;

@Configuration
@EnableWebSecurity
public class UserConfig { 
	
	@Autowired
	CustomUSerDetailService customuserdetail;
	
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http)
	{
		http.csrf(csrf -> csrf.disable());
		
		http.authorizeHttpRequests(auth -> {auth
			
	
		.requestMatchers("/admin").hasRole("ADMIN");
		
		
	auth.requestMatchers("/user").hasRole("USER");
	
	auth  .requestMatchers("/start","/hello").permitAll().anyRequest().authenticated();
				
				
				
				
		}).httpBasic(Customizer.withDefaults()).formLogin(form -> form.permitAll());
				
				
				return http.build();

	}
	
	 public DaoAuthenticationProvider authenManager()
	 {
		 
		 DaoAuthenticationProvider  provider = new DaoAuthenticationProvider(customuserdetail);
		 
		
		 provider.setPasswordEncoder(encoder());
		 
		 return provider;
		 
	 }
	 
	 
	 public void provicder()
	 {
		 
		 DaoAuthenticationProvider provider =new DaoAuthenticationProvider(customuserdetail);
		 
		 
		 provider.setPasswordEncoder(encoder());
		 
		 
	 }
	
	
	
	
	@Bean
	public BCryptPasswordEncoder  encoder()
	{
		
		return new BCryptPasswordEncoder();
		
	}
	
	
	
}
