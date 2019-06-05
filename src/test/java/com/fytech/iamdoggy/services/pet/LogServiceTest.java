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

import com.fytech.iamdoggy.dtos.doggy.ActivityLogDTO;
import com.fytech.iamdoggy.dtos.doggy.DogDTO;
import com.fytech.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.fytech.iamdoggy.dtos.management.UserDTO;
import com.fytech.iamdoggy.enums.PetType;
import com.fytech.iamdoggy.interfaces.daos.doggy.DogJpaDAO;
import com.fytech.iamdoggy.interfaces.pet.DoggyService;
import com.fytech.iamdoggy.interfaces.pet.LogService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogServiceTest {
	
	@Autowired
	private LogService logService;
	
	private DogDTO dogDTO;
	private UserDTO userDTO;
	private ActivityLogDTO activityLogDTO;
	
	@Before
	public void setUp() throws Exception {	
		userDTO = new UserDTO();
		userDTO.setUsername("feiyu");
		userDTO.setUid("1");
		
		PetBreedConfigureDTO dogBreedConfigureDTO = new PetBreedConfigureDTO();
		dogBreedConfigureDTO.setType(PetType.dog);
		dogBreedConfigureDTO.setBreed("dog1");
		dogBreedConfigureDTO.setRarity(0.05);
		
		dogDTO = new DogDTO(userDTO, dogBreedConfigureDTO);
		dogDTO.setId(1l);
		
		activityLogDTO = new ActivityLogDTO();
		activityLogDTO.setId(1l);
		activityLogDTO.setPid(dogDTO.getId());
		activityLogDTO.setLog("test1\ntest2\ntest3\ntest4\ntest5\ntest6\ntest7\n");
	}
	
	@Test
	public void appendLog() {
		String tempLogString = "test log";
		logService.appendLog(activityLogDTO, tempLogString);
		assertTrue(activityLogDTO.getLog().contains(tempLogString));
	}
	
	@Test
	public void generateNewLog() {
		ActivityLogDTO newActivityLogDTO = logService.generateNewLog(dogDTO);
		assertNotNull(newActivityLogDTO);
		assertNotEquals(newActivityLogDTO.getId(), activityLogDTO.getId());
		assertNull(logService.generateNewLog(null));
	}
	
	@Test
	public void getTodayLog() {
		ActivityLogDTO todayActivityLogDTO = logService.getTodayLog(dogDTO);
		assertNotNull(todayActivityLogDTO);
		ActivityLogDTO todayActivityLogDTO2 = logService.getTodayLog(dogDTO);
		assertNotNull(todayActivityLogDTO2);
		assertEquals(todayActivityLogDTO.getId(), todayActivityLogDTO2.getId());
		assertNull(logService.getTodayLog(null));
		
	}
	
	@Test
	public void limitLatestLogs() {
		ActivityLogDTO tempActivityLogDTO = new ActivityLogDTO();
		tempActivityLogDTO.setLog(activityLogDTO.getLog());
		logService.limitLatestLogs(tempActivityLogDTO, 2);
		assertNotNull(tempActivityLogDTO.getLog());
		assertNotEquals(activityLogDTO.getLog(), tempActivityLogDTO.getLog());
		assertEquals("test7\ntest6\n", tempActivityLogDTO.getLog());
	}
}
