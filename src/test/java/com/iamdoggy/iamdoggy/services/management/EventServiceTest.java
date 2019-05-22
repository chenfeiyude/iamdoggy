package com.iamdoggy.iamdoggy.services.management;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.iamdoggy.iamdoggy.dtos.doggy.DogDTO;
import com.iamdoggy.iamdoggy.dtos.management.AccountDTO;
import com.iamdoggy.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.iamdoggy.iamdoggy.dtos.management.UserDTO;
import com.iamdoggy.iamdoggy.enums.PetHealthyState;
import com.iamdoggy.iamdoggy.enums.PetState;
import com.iamdoggy.iamdoggy.enums.PetType;
import com.iamdoggy.iamdoggy.interfaces.daos.management.AccountJpaDAO;
import com.iamdoggy.iamdoggy.interfaces.daos.management.PetBreedConfigureJpaDAO;
import com.iamdoggy.iamdoggy.interfaces.management.EventService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceTest {
	
	@MockBean
	private AccountJpaDAO accountJpaDAO;
	
	@MockBean
	private PetBreedConfigureJpaDAO dogBreedConfigureJpaDAO;
	
	@Autowired
	private EventService eventService;
	
	private UserDTO userDTO;
	private UserDTO userDTO2; // does not have enough credit
	private AccountDTO accountDTO;
	private List<PetBreedConfigureDTO> dogBreedConfigureDTOs;
	@Before
	public void setUp() throws Exception {		
		userDTO = new UserDTO();
		userDTO.setUsername("feiyu");
		userDTO.setUid("1");
		accountDTO = new AccountDTO();
		Mockito.when(accountJpaDAO.findByUid(userDTO.getUid())).thenReturn(accountDTO);
		
		userDTO2 = new UserDTO();
		userDTO2.setUsername("feiyu");
		userDTO2.setUid("2");
		accountDTO = new AccountDTO();
		accountDTO.setCredit(0);
		Mockito.when(accountJpaDAO.findByUid(userDTO2.getUid())).thenReturn(accountDTO);
		
		dogBreedConfigureDTOs = new ArrayList<>();
		PetBreedConfigureDTO dogBreedConfigureDTO = new PetBreedConfigureDTO();
		dogBreedConfigureDTO.setType(PetType.dog);
		dogBreedConfigureDTO.setBreed("dog1");
		dogBreedConfigureDTO.setRarity(0.05);
		dogBreedConfigureDTOs.add(dogBreedConfigureDTO);
		dogBreedConfigureDTO = new PetBreedConfigureDTO();
		dogBreedConfigureDTO.setType(PetType.dog);
		dogBreedConfigureDTO.setBreed("dog2");
		dogBreedConfigureDTO.setRarity(0.50);
		dogBreedConfigureDTOs.add(dogBreedConfigureDTO);
		Mockito.when(dogBreedConfigureJpaDAO.findAllByType(PetType.dog)).thenReturn(dogBreedConfigureDTOs);
	}
	
	@Test
	public void findRandomDogSuccess() {
		DogDTO dogDTO = eventService.findRandomDog(userDTO);
		assertNotNull(dogDTO);
		assertEquals(PetState.live, dogDTO.getState());
		assertEquals(PetHealthyState.healthy, dogDTO.getHealthy());
	}
	
	@Test
	public void findRandomDogFailed() {
		DogDTO dogDTO = eventService.findRandomDog(userDTO2);
		assertNull(dogDTO);
	}
}
