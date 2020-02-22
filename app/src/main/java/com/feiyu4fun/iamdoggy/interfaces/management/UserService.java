package com.feiyu4fun.iamdoggy.interfaces.management;

import com.feiyu4fun.iamdoggy.dtos.management.AccountDTO;

public interface UserService {
	AccountDTO getAccountDTO(String uid);
}
