package com.iamdoggy.iamdoggy.services.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamdoggy.iamdoggy.dtos.common.PetDTO;
import com.iamdoggy.iamdoggy.dtos.doggy.ActivityLogDTO;
import com.iamdoggy.iamdoggy.dtos.doggy.DogDTO;
import com.iamdoggy.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.iamdoggy.iamdoggy.dtos.management.UserDTO;
import com.iamdoggy.iamdoggy.interfaces.daos.doggy.DogJpaDAO;
import com.iamdoggy.iamdoggy.interfaces.pet.GeneratorService;
import com.iamdoggy.iamdoggy.interfaces.pet.LogService;

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
