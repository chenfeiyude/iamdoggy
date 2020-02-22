package com.feiyu4fun.iamdoggy.services.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feiyu4fun.iamdoggy.dtos.common.PetDTO;
import com.feiyu4fun.iamdoggy.dtos.doggy.ActivityLogDTO;
import com.feiyu4fun.iamdoggy.dtos.doggy.DogDTO;
import com.feiyu4fun.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.feiyu4fun.iamdoggy.dtos.management.UserDTO;
import com.feiyu4fun.iamdoggy.interfaces.daos.doggy.DogJpaDAO;
import com.feiyu4fun.iamdoggy.interfaces.pet.GeneratorService;
import com.feiyu4fun.iamdoggy.interfaces.pet.LogService;

@Service("dogGeneratorService")
public class DogGeneratorServiceImpl implements GeneratorService {
	
	@Autowired
	private DogJpaDAO dogJpaDAO;
	
	@Autowired
	private LogService logService;
	
	@Override
	public PetDTO generatePet(PetBreedConfigureDTO petBreedConfigureDTO, UserDTO userDTO) {
		DogDTO dogDTO = new DogDTO(userDTO, petBreedConfigureDTO);
		if (dogJpaDAO.findFirstByUid(userDTO.getUid()) == null) {
			dogDTO.setPrimary(true);// first one will be default to primary
		}
		
		dogDTO.setSpeed(getRandomAbility());
		dogDTO.setAttack(getRandomAbility());
		dogDTO.setDefence(getRandomAbility());
		
		dogJpaDAO.save(dogDTO);
		
		ActivityLogDTO activityLogDTO = logService.generateNewLog(dogDTO);
		logService.appendLog(activityLogDTO, "New born");
		
		return dogDTO;
	}

}
