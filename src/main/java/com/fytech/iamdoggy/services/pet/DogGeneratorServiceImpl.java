package com.fytech.iamdoggy.services.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fytech.iamdoggy.dtos.common.PetDTO;
import com.fytech.iamdoggy.dtos.doggy.ActivityLogDTO;
import com.fytech.iamdoggy.dtos.doggy.DogDTO;
import com.fytech.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.fytech.iamdoggy.dtos.management.UserDTO;
import com.fytech.iamdoggy.interfaces.daos.doggy.DogJpaDAO;
import com.fytech.iamdoggy.interfaces.pet.GeneratorService;
import com.fytech.iamdoggy.interfaces.pet.LogService;

@Service("dogGeneratorService")
public class DogGeneratorServiceImpl implements GeneratorService {
	
	@Autowired
	private DogJpaDAO dogJpaDAO;
	
	@Autowired
	private LogService logService;
	
	@Override
	public PetDTO generatePet(PetBreedConfigureDTO petBreedConfigureDTO, UserDTO userDTO) {
		DogDTO dogDTO = new DogDTO(userDTO, petBreedConfigureDTO);
		dogJpaDAO.save(dogDTO);
		
		ActivityLogDTO activityLogDTO = logService.generateNewLog(dogDTO);
		logService.appendLog(activityLogDTO, "New born");
		
		return dogDTO;
	}

}
