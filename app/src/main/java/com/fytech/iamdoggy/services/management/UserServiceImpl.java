package com.fytech.iamdoggy.services.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fytech.iamdoggy.dtos.management.AccountDTO;
import com.fytech.iamdoggy.interfaces.daos.management.AccountJpaDAO;
import com.fytech.iamdoggy.interfaces.management.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private AccountJpaDAO accountJpaDAO;
	@Override
	public AccountDTO getAccountDTO(String uid) {
		return accountJpaDAO.findByUid(uid);
	}

}
