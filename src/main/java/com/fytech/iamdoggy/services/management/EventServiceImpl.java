package com.fytech.iamdoggy.services.management;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fytech.iamdoggy.configurations.MiscConfigure;
import com.fytech.iamdoggy.dtos.common.PetDTO;
import com.fytech.iamdoggy.dtos.doggy.ActivityLogDTO;
import com.fytech.iamdoggy.dtos.doggy.DogDTO;
import com.fytech.iamdoggy.dtos.doggy.EventDTO;
import com.fytech.iamdoggy.dtos.management.AccountDTO;
import com.fytech.iamdoggy.dtos.management.EventConfigureDTO;
import com.fytech.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.fytech.iamdoggy.dtos.management.UserDTO;
import com.fytech.iamdoggy.enums.PetType;
import com.fytech.iamdoggy.interfaces.daos.doggy.EventJpaDAO;
import com.fytech.iamdoggy.interfaces.daos.management.AccountJpaDAO;
import com.fytech.iamdoggy.interfaces.daos.management.EventConfigureJpaDAO;
import com.fytech.iamdoggy.interfaces.daos.management.PetBreedConfigureJpaDAO;
import com.fytech.iamdoggy.interfaces.daos.management.UserJpaDAO;
import com.fytech.iamdoggy.interfaces.management.EventService;
import com.fytech.iamdoggy.interfaces.pet.GeneratorService;
import com.fytech.iamdoggy.interfaces.pet.LogService;
import com.fytech.iamdoggy.utils.Utils;

@Service("eventService")
@Slf4j
public class EventServiceImpl implements EventService {
	
	@Autowired
	private PetBreedConfigureJpaDAO petBreedConfigureJpaDAO;
	
	@Autowired
	private UserJpaDAO userJpaDAO;
	
	@Autowired
	private EventConfigureJpaDAO eventConfigureJpaDAO;
	
	@Autowired
	private AccountJpaDAO accountJpaDAO;
	
	@Autowired
	private EventJpaDAO eventJpaDAO;
	
	@Autowired
	private GeneratorService dogGeneratorService;
	
	@Autowired LogService logService;
	
	@Override
	public DogDTO findRandomDog(UserDTO userDTO) {
		DogDTO dogDTO = null;
		AccountDTO accountDTO = accountJpaDAO.findByUid(userDTO.getUid());
		if (accountDTO.getCredit() < MiscConfigure.DEFAULT_FIND_DOG_COST) {
			// user cannot afford this search
			return dogDTO;
		}
		
		List<PetBreedConfigureDTO> dogBreedConfigureDTOs = petBreedConfigureJpaDAO.findAllByType(PetType.dog);
		
		int index = Utils.getRandomPossibility(dogBreedConfigureDTOs);
		
		PetBreedConfigureDTO dogBreedConfigureDTO = dogBreedConfigureDTOs.get(index);
		
		dogDTO = (DogDTO) dogGeneratorService.generatePet(dogBreedConfigureDTO, userDTO);

		accountDTO.subCredit(MiscConfigure.DEFAULT_FIND_DOG_COST);
		accountJpaDAO.save(accountDTO);
		return dogDTO;
	}

	@Override
	public EventDTO getRandomEvent(PetDTO petDTO) {
		List<EventConfigureDTO> eventConfigureDTOs = eventConfigureJpaDAO.findAll();
		int index = Utils.getRandomPossibility(eventConfigureDTOs);
		EventConfigureDTO eventConfigureDTO = eventConfigureDTOs.get(index);
		
		EventDTO eventDTO = new EventDTO(petDTO, eventConfigureDTO);
		if (eventConfigureDTO.isPersist()) {
			eventJpaDAO.save(eventDTO);
		}
		return eventDTO;
	}

	@Override
	public void processEvent(PetDTO petDTO, EventDTO eventDTO) {
		log.info("Processing pid " + petDTO.getId() + " event " + eventDTO.getType());
		
		ActivityLogDTO activityLogDTO = logService.getTodayLog(petDTO);
		logService.appendLog(activityLogDTO, eventDTO.toString());
	}

	
}
