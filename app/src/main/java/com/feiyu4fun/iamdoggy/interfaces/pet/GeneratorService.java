package com.feiyu4fun.iamdoggy.interfaces.pet;

import com.feiyu4fun.iamdoggy.configurations.MiscConfigure;
import com.feiyu4fun.iamdoggy.dtos.common.PetDTO;
import com.feiyu4fun.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.feiyu4fun.iamdoggy.dtos.management.UserDTO;

public interface GeneratorService {
	PetDTO generatePet(PetBreedConfigureDTO petBreedConfigureDTO, UserDTO userDTO);
	
	default int getRandomAbility() {
		return (int) (Math.random() * MiscConfigure.MAX_INIT_ABILITY);
	}
}
