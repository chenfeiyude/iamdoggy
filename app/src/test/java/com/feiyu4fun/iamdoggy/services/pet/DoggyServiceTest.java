package com.feiyu4fun.iamdoggy.services.pet;

import static org.junit.Assert.*;

import java.util.Optional;

import com.feiyu4fun.iamdoggy.dtos.doggy.DogDTO;
import com.feiyu4fun.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.feiyu4fun.iamdoggy.dtos.management.UserDTO;
import com.feiyu4fun.iamdoggy.enums.PetType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.feiyu4fun.iamdoggy.interfaces.daos.doggy.DogJpaDAO;
import com.feiyu4fun.iamdoggy.interfaces.pet.DoggyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoggyServiceTest {
	@MockBean
	private DogJpaDAO dogJpaDAO; 
	
	@Autowired
	private DoggyService doggyService;
	
	private Optional<DogDTO> dogDTO;
	private Optional<DogDTO> dogDTO2;
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
		dogDTO.get().setPrimary(true);

		dogDTO2 = Optional.of(new DogDTO(userDTO, dogBreedConfigureDTO)) ;
		dogDTO2.get().setId(2l);
		dogDTO2.get().setPrimary(false);

		Mockito.when(dogJpaDAO.findById(1l)).thenReturn(dogDTO);
		Mockito.when(dogJpaDAO.findById(2l)).thenReturn(dogDTO2);
		Mockito.when(dogJpaDAO.findByUidAndIsPrimary(userDTO.getUid(), true)).thenReturn(dogDTO.get());
	}
	
	@Test
	public void getDog() {
		DogDTO tempDogDTO = doggyService.getDog(userDTO, 1l);
		assertNotNull(tempDogDTO);
		Assert.assertEquals(dogDTO.get().getId(), tempDogDTO.getId());
		assertEquals(dogDTO.get().getBreed(), tempDogDTO.getBreed());
		Assert.assertEquals(dogDTO.get().getState(), tempDogDTO.getState());
		Assert.assertEquals(dogDTO.get().getUid(), tempDogDTO.getUid());
		Assert.assertEquals(dogDTO.get().getCost(), tempDogDTO.getCost());
		
		tempDogDTO = doggyService.getDog(userDTO, 1000l);
		assertNull(tempDogDTO);
		
		tempDogDTO = doggyService.getDog(null, 1l);
		assertNull(tempDogDTO);
	}
	
	@Test
	public void getPrimaryDog() {
		DogDTO tempDogDTO = doggyService.getPrimaryDog(userDTO);
		assertNotNull(tempDogDTO);
		Assert.assertTrue(tempDogDTO.isPrimary());
	}

	@Test
	public void setPrimaryDog() {
		DogDTO tempDogDTO = doggyService.setPrimaryDog(userDTO, 2l);
		assertNotNull(tempDogDTO);
		Assert.assertTrue(tempDogDTO.isPrimary());

		DogDTO tempDogDTO2 = doggyService.getDog(userDTO, 1l);
		assertNotNull(tempDogDTO2);
		Assert.assertFalse(tempDogDTO2.isPrimary());
	}
}
