package com.fytech.iamdoggy.interfaces.pet;

import com.fytech.iamdoggy.configurations.MiscConfigure;
import com.fytech.iamdoggy.dtos.common.PetDTO;
import com.fytech.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.fytech.iamdoggy.dtos.management.UserDTO;

public interface GeneratorService {
	PetDTO generatePet(PetBreedConfigureDTO petBreedConfigureDTO, UserDTO userDTO);
	
	default int getRandomAbility() {
		return (int) (Math.random() * MiscConfigure.MAX_INIT_ABILITY);
	}
}
