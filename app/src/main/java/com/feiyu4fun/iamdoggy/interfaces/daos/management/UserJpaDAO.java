package com.feiyu4fun.iamdoggy.interfaces.daos.management;

import com.feiyu4fun.iamdoggy.dtos.management.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userJpaDAO")
public interface UserJpaDAO extends JpaRepository<UserDTO, Long> {
	UserDTO findByUsername(String username);
	UserDTO findByUsernameAndToken(String username, String token);
	
}
