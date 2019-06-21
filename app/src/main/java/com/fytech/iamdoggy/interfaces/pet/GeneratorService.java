package com.fytech.iamdoggy.interfaces.pet;

import com.fytech.iamdoggy.dtos.common.PetDTO;
import com.fytech.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.fytech.iamdoggy.dtos.management.UserDTO;

public interface GeneratorService {
	PetDTO generatePet(PetBreedConfigureDTO petBreedConfigureDTO, UserDTO userDTO);
}
