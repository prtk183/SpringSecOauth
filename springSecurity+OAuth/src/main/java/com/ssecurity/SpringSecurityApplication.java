package com.ssecurity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ssecurity.model.Authority;
import com.ssecurity.model.Role;
import com.ssecurity.model.User;
import com.ssecurity.repository.IUserRepository;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {
	
	
	@Autowired
	IUserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
		
		Set<Authority> adminAuthority = new HashSet<>();
		adminAuthority.add(new Authority("UPDATE"));
		adminAuthority.add(new Authority("DELETE"));
		adminAuthority.add(new Authority("VIEW"));
		
		Set<Authority> userAuthority = new HashSet<>();
		adminAuthority.add(new Authority("VIEW"));
		
		Set<Role> proles = new HashSet<>();
		proles.add(new Role("ADMIN",adminAuthority));
		proles.add(new Role("USER", userAuthority));
		User user = new User("sun", "moon", proles) ;
		repository.saveAndFlush(user);
		
		Set<Role> wRole = new HashSet<>();
		wRole.add(new Role("USER", userAuthority));
		User user2 = new User("bat", "ball", wRole) ;
		repository.saveAndFlush(user2);
	}
}
