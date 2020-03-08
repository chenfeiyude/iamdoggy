package com.feiyu4fun.iamdoggy.controllers.pet;

import com.feiyu4fun.iamdoggy.dtos.doggy.ActivityLogDTO;
import com.feiyu4fun.iamdoggy.dtos.doggy.DogDTO;
import com.feiyu4fun.iamdoggy.dtos.doggy.EventDTO;
import com.feiyu4fun.iamdoggy.dtos.management.UserDTO;
import com.feiyu4fun.iamdoggy.interfaces.management.AuthService;
import com.feiyu4fun.iamdoggy.interfaces.management.EventService;
import com.feiyu4fun.iamdoggy.interfaces.pet.DoggyService;
import com.feiyu4fun.iamdoggy.interfaces.pet.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
	private EventService eventService;
	
	@Autowired
	private DoggyService doggyService;
	
	/**
	 * http://localhost:8080/iamdoggy-0.0.1-SNAPSHOT/api/doggy/activity_log/get?pid=1
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/activity/get")
    public ActivityLogDTO getActivity(HttpServletRequest request,
                                      @RequestParam(name="pid") long pid,
                                      @RequestParam(name="limit", required=false) int limit) {
		UserDTO userDTO = authService.getUserFromSession(request);
		DogDTO dogDTO = doggyService.getDog(userDTO, pid);
		if (dogDTO == null) {
			throw new IllegalArgumentException("Invalid pid");
		}
		ActivityLogDTO activityLogDTO = logService.getTodayLog(dogDTO);
		if (limit > 0) {
			// get latest ones
			logService.limitLatestLogs(activityLogDTO, limit);
		}
		return activityLogDTO;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/activity/generate")
    public ActivityLogDTO generateActivity(HttpServletRequest request, 
    									@RequestParam(name="pid") long pid) {
		UserDTO userDTO = authService.getUserFromSession(request);
		DogDTO dogDTO = doggyService.getDog(userDTO, pid);
		if (dogDTO == null) {
			throw new IllegalArgumentException("Invalid pid");
		}
		
		EventDTO eventDTO = eventService.getRandomEvent(dogDTO);
		eventService.processEvent(dogDTO, eventDTO);
		
		ActivityLogDTO activityLogDTO = logService.getTodayLog(dogDTO);
		return activityLogDTO;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/dogs/get")
    public List<DogDTO> getDogs(HttpServletRequest request) {
		UserDTO userDTO = authService.getUserFromSession(request);
		List<DogDTO> dogDTOs = doggyService.getDogs(userDTO);
		return dogDTOs;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/dogs/get/primary")
    public DogDTO getPrimaryDog(HttpServletRequest request) {
		UserDTO userDTO = authService.getUserFromSession(request);
		DogDTO dogDTO = doggyService.getPrimaryDog(userDTO);
		if (dogDTO == null) {
			log.info("No primary found for user");
		}
		return dogDTO;
	}

	@RequestMapping(method=RequestMethod.POST, value="/dogs/set/primary")
	public DogDTO setPrimaryDog(HttpServletRequest request,
								@RequestParam(name="pid") long pid) {
		UserDTO userDTO = authService.getUserFromSession(request);
		DogDTO dogDTO = doggyService.setPrimaryDog(userDTO, pid);
		if (dogDTO == null) {
			throw new IllegalArgumentException("Invalid pid");
		}

		return dogDTO;
	}
}
