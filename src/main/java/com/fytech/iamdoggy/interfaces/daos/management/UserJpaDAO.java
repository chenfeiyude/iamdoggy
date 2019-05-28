package com.fytech.iamdoggy.interfaces.daos.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fytech.iamdoggy.dtos.management.UserDTO;

@Repository("userJpaDAO")
public interface UserJpaDAO extends JpaRepository<UserDTO, Long> {
	UserDTO findByUsername(String username);
	UserDTO findByUsernameAndToken(String username, String token);
	
}
