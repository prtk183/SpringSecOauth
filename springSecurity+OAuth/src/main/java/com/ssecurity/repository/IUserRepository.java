package com.ssecurity.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssecurity.model.User;

public interface IUserRepository extends JpaRepository<User, Serializable>{

	@Query
	Optional<User> findByuserName(String username);

}
