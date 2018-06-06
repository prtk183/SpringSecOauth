package com.ssecurity.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ssecurity.model.Authority;
import com.ssecurity.model.Role;
import com.ssecurity.model.User;
import com.ssecurity.repository.IUserRepository;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;



public class UserServiceImpl implements UserDetailsService{

	
private  IUserRepository repository;
	
	public UserServiceImpl(IUserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  Optional<User> optionalUser =  repository.findByuserName(username);
	  optionalUser
	  .orElseThrow(() -> new  UsernameNotFoundException("Username is not available"));
	  return optionalUser.map(user -> 
	  new org.springframework.security.core.userdetails.User(user.getUserName(), 
			  user.getPassword(), getAuthorities(user.getRoles()))).get();
	}


/*	private Collection<? extends GrantedAuthority> getAuthorities(User user) {
		return user.getRoles().stream().map(role-> 
			new SimpleGrantedAuthority("ROLE_" + role.getRole()))
		.collect(Collectors.toList());
	}
	*/
	
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {

		return getGrantedAuthorities(getAuthorites(roles));
	}

	private List<String> getAuthorites(Collection<Role> roles) {

		List<String> Authorites = new ArrayList<>();
		List<Authority> collection = new ArrayList<>();
		for (Role role : roles) {
			collection.addAll(role.getAuthority());
		}
		for (Authority item : collection) {
			Authorites.add(item.getAuthority());
		}
		return Authorites;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> Authorites) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String Authority : Authorites) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+Authority));
		}
		return authorities;
	}

	
	
}
