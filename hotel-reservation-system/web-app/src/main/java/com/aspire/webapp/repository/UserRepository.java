package com.aspire.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aspire.webapp.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	@Query("SELECT username FROM users WHERE username = ?1")
	public Users findByUsername(String username);
}