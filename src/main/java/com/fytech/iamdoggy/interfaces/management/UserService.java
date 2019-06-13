package com.fytech.iamdoggy.interfaces.management;

import com.fytech.iamdoggy.dtos.management.AccountDTO;

public interface UserService {
	AccountDTO getAccountDTO(String uid);
}
