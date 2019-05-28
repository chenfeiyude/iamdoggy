package com.fytech.iamdoggy.controllers.pet;

import javassist.NotFoundException;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fytech.iamdoggy.dtos.doggy.ActivityLogDTO;
import com.fytech.iamdoggy.dtos.doggy.DogDTO;
import com.fytech.iamdoggy.dtos.management.UserDTO;
import com.fytech.iamdoggy.interfaces.management.AuthService;
import com.fytech.iamdoggy.interfaces.pet.DoggyService;
import com.fytech.iamdoggy.interfaces.pet.LogService;

@RestController
@RequestMapping(value="/api/doggy")
@Validated
@Slf4j
public class DoggyController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private DoggyService doggyService;
	
	/**
	 * http://localhost:8080/iamdoggy-0.0.1-SNAPSHOT/api/doggy/activity_log/get?pid=1
	 * @param request
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping(method=RequestMethod.GET, value="/activity_log/get")
    public ActivityLogDTO findRandomDog(HttpServletRequest request, 
    									@RequestParam(name="pid") long pid) throws NotFoundException {
		UserDTO userDTO = authService.getUserFromSession(request);
		DogDTO dogDTO = doggyService.getDog(userDTO, pid);
		if (dogDTO == null) {
			throw new IllegalArgumentException("Invalid pid");
		}
		ActivityLogDTO activityLogDTO = logService.getTodayLog(dogDTO);
		return activityLogDTO;
	}
}
