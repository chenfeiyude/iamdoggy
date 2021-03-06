package com.feiyu4fun.iamdoggy.services.pet;

import static org.junit.Assert.*;

import com.feiyu4fun.iamdoggy.dtos.common.PetDTO;
import com.feiyu4fun.iamdoggy.dtos.doggy.DogDTO;
import com.feiyu4fun.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.feiyu4fun.iamdoggy.dtos.management.UserDTO;
import com.feiyu4fun.iamdoggy.enums.PetState;
import com.feiyu4fun.iamdoggy.enums.PetType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.feiyu4fun.iamdoggy.configurations.MiscConfigure;
import com.feiyu4fun.iamdoggy.interfaces.pet.GeneratorService;

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
		assertTrue(petDTO.isPrimary());
		assertEquals(0, petDTO.getAge());
		Assert.assertEquals(PetState.live, petDTO.getState());
		assertEquals(DogDTO.class, petDTO.getClass());
		assertEquals(dogBreedConfigureDTO.getBreed(), ((DogDTO)petDTO).getBreed());
		
		assertEquals(0, petDTO.getLevel());
		assertTrue(petDTO.getSpeed() < MiscConfigure.MAX_INIT_ABILITY);
		assertTrue(petDTO.getAttack() < MiscConfigure.MAX_INIT_ABILITY);
		assertTrue(petDTO.getDefence() < MiscConfigure.MAX_INIT_ABILITY);
		
	}
}
