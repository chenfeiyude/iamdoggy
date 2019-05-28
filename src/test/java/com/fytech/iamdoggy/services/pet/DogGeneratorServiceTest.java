package com.fytech.iamdoggy.services.pet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fytech.iamdoggy.dtos.common.PetDTO;
import com.fytech.iamdoggy.dtos.doggy.DogDTO;
import com.fytech.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.fytech.iamdoggy.dtos.management.UserDTO;
import com.fytech.iamdoggy.enums.PetState;
import com.fytech.iamdoggy.enums.PetType;
import com.fytech.iamdoggy.interfaces.pet.GeneratorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DogGeneratorServiceTest {
	
	@Autowired
	private GeneratorService dogGeneratorService;
	
	private PetBreedConfigureDTO dogBreedConfigureDTO;
	private UserDTO userDTO;
	
	@Before
	public void setUp() throws Exception {		
		userDTO = new UserDTO();
		userDTO.setUsername("feiyu");
		userDTO.setUid("1");
		
		dogBreedConfigureDTO = new PetBreedConfigureDTO();
		dogBreedConfigureDTO.setType(PetType.dog);
		dogBreedConfigureDTO.setBreed("dog1");
		dogBreedConfigureDTO.setRarity(0.05);
		
	}
	
	@Test
	public void generatePet() {
		PetDTO petDTO = dogGeneratorService.generatePet(dogBreedConfigureDTO, userDTO);
		assertNotNull(petDTO);
		assertEquals(0, petDTO.getAge());
		assertEquals(PetState.live, petDTO.getState());
		assertEquals(DogDTO.class, petDTO.getClass());
		assertEquals(dogBreedConfigureDTO.getBreed(), ((DogDTO)petDTO).getBreed());
		
	}
}
