package com.feiyu4fun.iamdoggy.services.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feiyu4fun.iamdoggy.dtos.management.AccountDTO;
import com.feiyu4fun.iamdoggy.interfaces.daos.management.AccountJpaDAO;
import com.feiyu4fun.iamdoggy.interfaces.management.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private AccountJpaDAO accountJpaDAO;
	@Override
	public AccountDTO getAccountDTO(String uid) {
		return accountJpaDAO.findByUid(uid);
	}

}
