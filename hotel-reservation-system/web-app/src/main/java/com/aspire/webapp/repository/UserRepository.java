package com.aspire.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aspire.webapp.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	@Query(value= "SELECT * FROM users WHERE username = :username", nativeQuery = true)
	public Users findByUsername(@Param("username") String username);
}