package com.feiyu4fun.iamdoggy.controllers.management;

import com.feiyu4fun.iamdoggy.dtos.doggy.DogDTO;
import com.feiyu4fun.iamdoggy.dtos.management.UserDTO;
import javassist.NotFoundException;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.feiyu4fun.iamdoggy.interfaces.management.AuthService;
import com.feiyu4fun.iamdoggy.interfaces.management.EventService;

//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping(value="/api/event")
@Validated
@Slf4j
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private AuthService authService;
	
	/**
	 * api/event/find_random_dog
	 * @param request
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping(method=RequestMethod.GET, value="/find_random_dog")
    public DogDTO findRandomDog(HttpServletRequest request) throws NotFoundException {
		UserDTO userDTO = authService.getUserFromSession(request);
		DogDTO dogDTO = eventService.findRandomDog(userDTO);
		if (dogDTO == null) {
			throw new RuntimeException("No dog found");
		}
		log.info("Found " + dogDTO.getBreed());
		return dogDTO;
	}
}
