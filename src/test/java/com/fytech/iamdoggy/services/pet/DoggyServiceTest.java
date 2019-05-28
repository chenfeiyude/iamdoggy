package com.fytech.iamdoggy.services.pet;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fytech.iamdoggy.dtos.doggy.DogDTO;
import com.fytech.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.fytech.iamdoggy.dtos.management.UserDTO;
import com.fytech.iamdoggy.enums.PetType;
import com.fytech.iamdoggy.interfaces.daos.doggy.DogJpaDAO;
import com.fytech.iamdoggy.interfaces.pet.DoggyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoggyServiceTest {
	@MockBean
	private DogJpaDAO dogJpaDAO; 
	
	@Autowired
	private DoggyService doggyService;
	
	private Optional<DogDTO> dogDTO;
	private UserDTO userDTO;
	
	@Before
	public void setUp() throws Exception {	
		userDTO = new UserDTO();
		userDTO.setUsername("feiyu");
		userDTO.setUid("1");
		
		PetBreedConfigureDTO dogBreedConfigureDTO = new PetBreedConfigureDTO();
		dogBreedConfigureDTO.setType(PetType.dog);
		dogBreedConfigureDTO.setBreed("dog1");
		dogBreedConfigureDTO.setRarity(0.05);
		
		dogDTO = Optional.of(new DogDTO(userDTO, dogBreedConfigureDTO)) ;
		dogDTO.get().setId(1l);
		Mockito.when(dogJpaDAO.findById(1l)).thenReturn(dogDTO);
		
	}
	
	@Test
	public void getDog() {
		DogDTO tempDogDTO = doggyService.getDog(userDTO, 1l);
		assertNotNull(tempDogDTO);
		assertEquals(dogDTO.get().getId(), tempDogDTO.getId());
		assertEquals(dogDTO.get().getBreed(), tempDogDTO.getBreed());
		assertEquals(dogDTO.get().getState(), tempDogDTO.getState());
		assertEquals(dogDTO.get().getUid(), tempDogDTO.getUid());
		assertEquals(dogDTO.get().getCost(), tempDogDTO.getCost());
		
		tempDogDTO = doggyService.getDog(userDTO, 2l);
		assertNull(tempDogDTO);
		
		tempDogDTO = doggyService.getDog(null, 1l);
		assertNull(tempDogDTO);
	}
}
