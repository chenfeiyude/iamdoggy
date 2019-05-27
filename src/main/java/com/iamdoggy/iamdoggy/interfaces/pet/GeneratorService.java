package com.iamdoggy.iamdoggy.interfaces.pet;

import com.iamdoggy.iamdoggy.dtos.common.PetDTO;
import com.iamdoggy.iamdoggy.dtos.doggy.EventDTO;
import com.iamdoggy.iamdoggy.dtos.management.EventConfigureDTO;
import com.iamdoggy.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.iamdoggy.iamdoggy.dtos.management.UserDTO;

public interface GeneratorService {
	PetDTO generatePet(PetBreedConfigureDTO petBreedConfigureDTO, UserDTO userDTO);
}
