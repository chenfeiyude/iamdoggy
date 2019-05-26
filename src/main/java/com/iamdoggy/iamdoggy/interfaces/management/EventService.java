package com.iamdoggy.iamdoggy.interfaces.management;

import com.iamdoggy.iamdoggy.dtos.common.PetDTO;
import com.iamdoggy.iamdoggy.dtos.doggy.DogDTO;
import com.iamdoggy.iamdoggy.dtos.doggy.EventDTO;
import com.iamdoggy.iamdoggy.dtos.management.UserDTO;

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
}
