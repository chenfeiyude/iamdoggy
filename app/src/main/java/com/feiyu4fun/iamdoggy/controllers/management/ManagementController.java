package com.feiyu4fun.iamdoggy.controllers.management;

import javax.servlet.http.HttpServletRequest;

import com.feiyu4fun.iamdoggy.dtos.management.AccountDTO;
import com.feiyu4fun.iamdoggy.dtos.management.UserDTO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.feiyu4fun.iamdoggy.interfaces.management.AuthService;
import com.feiyu4fun.iamdoggy.interfaces.management.UserService;

@RestController
@RequestMapping(value="/api/management")
@Validated
@Slf4j
public class ManagementController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	/**
	 * api/management/account/get
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/account/get")
    public AccountDTO getAccount(HttpServletRequest request) {
		UserDTO userDTO = authService.getUserFromSession(request);
		
		AccountDTO accountDTO = userService.getAccountDTO(userDTO.getUid());
		return accountDTO;
	}
}
