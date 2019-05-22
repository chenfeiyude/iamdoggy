package com.iamdoggy.iamdoggy.controllers.management;

import javassist.NotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iamdoggy.iamdoggy.dtos.doggy.DogDTO;
import com.iamdoggy.iamdoggy.dtos.management.UserDTO;
import com.iamdoggy.iamdoggy.interfaces.management.AuthService;
import com.iamdoggy.iamdoggy.interfaces.management.EventService;

@RestController
@RequestMapping(value="/api/event")
@Validated
public class EventController {
private static Logger logger = LoggerFactory.getLogger(EventController.class.getName());
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private AuthService authService;
	
	/**
	 * http://localhost:8080/iamdoggy-0.0.1-SNAPSHOT/api/event/find_random_dog
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
		logger.info("Found " + dogDTO.getBreed());
		return dogDTO;
	}
}
