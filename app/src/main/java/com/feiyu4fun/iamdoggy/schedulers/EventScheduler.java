package com.feiyu4fun.iamdoggy.schedulers;

import java.util.List;

import com.feiyu4fun.iamdoggy.dtos.doggy.DogDTO;
import com.feiyu4fun.iamdoggy.dtos.doggy.EventDTO;
import com.feiyu4fun.iamdoggy.enums.PetState;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.feiyu4fun.iamdoggy.interfaces.daos.doggy.DogJpaDAO;
import com.feiyu4fun.iamdoggy.interfaces.management.EventService;


@Component
@Slf4j
public class EventScheduler {
	@Autowired
	private DogJpaDAO dogJpaDAO;
	
	@Autowired
	private EventService eventService;
	
//	@Scheduled(fixedRate=60*1000, initialDelay=60*1000)
	public void randomEvents() {
		log.info("randomEvents schedule running..............................");
		int page = 0;
		int size = 100;
		List<DogDTO> dogDTOs = dogJpaDAO.findAllByState(PetState.live, PageRequest.of(page, size));
		while (!dogDTOs.isEmpty()) {
			for (DogDTO dogDTO : dogDTOs) {
				EventDTO eventDTO = eventService.getRandomEvent(dogDTO);
				eventService.processEvent(dogDTO, eventDTO);
			}
			
			page += 1;
			dogDTOs = dogJpaDAO.findAllByState(PetState.live, PageRequest.of(page, size));
		}
	}
}
