package com.aspire.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aspire.webapp.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	public Users findByUserName(String userName);
}
