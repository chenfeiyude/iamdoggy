package com.iamdoggy.iamdoggy.services.management;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamdoggy.iamdoggy.configurations.MiscConfigure;
import com.iamdoggy.iamdoggy.dtos.common.PetDTO;
import com.iamdoggy.iamdoggy.dtos.doggy.ActivityLogDTO;
import com.iamdoggy.iamdoggy.dtos.doggy.DogDTO;
import com.iamdoggy.iamdoggy.dtos.doggy.EventDTO;
import com.iamdoggy.iamdoggy.dtos.management.AccountDTO;
import com.iamdoggy.iamdoggy.dtos.management.EventConfigureDTO;
import com.iamdoggy.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.iamdoggy.iamdoggy.dtos.management.UserDTO;
import com.iamdoggy.iamdoggy.enums.PetType;
import com.iamdoggy.iamdoggy.interfaces.daos.doggy.EventJpaDAO;
import com.iamdoggy.iamdoggy.interfaces.daos.management.AccountJpaDAO;
import com.iamdoggy.iamdoggy.interfaces.daos.management.EventConfigureJpaDAO;
import com.iamdoggy.iamdoggy.interfaces.daos.management.PetBreedConfigureJpaDAO;
import com.iamdoggy.iamdoggy.interfaces.daos.management.UserJpaDAO;
import com.iamdoggy.iamdoggy.interfaces.management.EventService;
import com.iamdoggy.iamdoggy.interfaces.pet.GeneratorService;
import com.iamdoggy.iamdoggy.interfaces.pet.LogService;
import com.iamdoggy.iamdoggy.utils.Utils;

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
