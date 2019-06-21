package com.fytech.iamdoggy.interfaces.management;

import com.fytech.iamdoggy.dtos.common.PetDTO;
import com.fytech.iamdoggy.dtos.doggy.DogDTO;
import com.fytech.iamdoggy.dtos.doggy.EventDTO;
import com.fytech.iamdoggy.dtos.management.UserDTO;

public interface EventService {
	
	/**
	 * A 0.01
	 * B 0.1
	 * C 0.7
	 * D 0.5
	 * 
	 * SUM = 0.01 + 0.1 + 0.7 + 0.1 = 0.91
	 * 
	 * A 0.1 / 0.91
	 * B (0.1 + 0.01) / 0.91
	 * C (0.7 + 0.1 + 0.01) / 0.91
	 * D (0.1 + 0.7 + 0.1 + 0.01) / 0.91
	 *  
	 * @param userDTO
	 * @return
	 */
	DogDTO findRandomDog(UserDTO userDTO);
	
	EventDTO getRandomEvent(PetDTO petDTO);
	
	/**
	 * Create related records like diseases for ill events
	 * @param petDTO
	 * @param eventDTO
	 */
	void processEvent(PetDTO petDTO, EventDTO eventDTO);
}
