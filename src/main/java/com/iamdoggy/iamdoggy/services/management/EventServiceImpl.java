package com.iamdoggy.iamdoggy.services.management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamdoggy.iamdoggy.configurations.MiscConfigure;
import com.iamdoggy.iamdoggy.dtos.doggy.DogDTO;
import com.iamdoggy.iamdoggy.dtos.management.AccountDTO;
import com.iamdoggy.iamdoggy.dtos.management.DogBreedConfigureDTO;
import com.iamdoggy.iamdoggy.dtos.management.UserDTO;
import com.iamdoggy.iamdoggy.interfaces.daos.doggy.DogJpaDAO;
import com.iamdoggy.iamdoggy.interfaces.daos.management.AccountJpaDAO;
import com.iamdoggy.iamdoggy.interfaces.daos.management.DogBreedConfigureJpaDAO;
import com.iamdoggy.iamdoggy.interfaces.daos.management.UserJpaDAO;
import com.iamdoggy.iamdoggy.interfaces.management.EventService;

@Service("eventService")
public class EventServiceImpl implements EventService {
	
	@Autowired
	private DogBreedConfigureJpaDAO dogBreedConfigureJpaDAO;
	
	@Autowired
	private UserJpaDAO userJpaDAO;
	
	@Autowired
	private AccountJpaDAO accountJpaDAO;
	
	@Autowired
	private DogJpaDAO dogJpaDAO;
	
	@Override
	public DogDTO findRandomDog(UserDTO userDTO) {
		DogDTO dogDTO = null;
		AccountDTO accountDTO = accountJpaDAO.findByUid(userDTO.getUid());
		if (accountDTO.getCredit() < MiscConfigure.DEFAULT_FIND_DOG_COST) {
			// user cannot afford this search
			return dogDTO;
		}
		
		List<DogBreedConfigureDTO> dogBreedConfigureDTOs = dogBreedConfigureJpaDAO.findAll();
		double sumRarity = dogBreedConfigureJpaDAO.getSumRarity();
		Double tempSumRarity = 0d;
		List<Double> sortProbabilityList = new ArrayList<Double>(dogBreedConfigureDTOs.size());
		for (DogBreedConfigureDTO dogBreedConfigureDTO : dogBreedConfigureDTOs) {
			tempSumRarity += dogBreedConfigureDTO.getRarity();
			sortProbabilityList.add(tempSumRarity/sumRarity);
		}
		
		double randomDouble = Math.random();
		sortProbabilityList.add(randomDouble);
		
		Collections.sort(sortProbabilityList);
		int breedIndex = sortProbabilityList.indexOf(randomDouble);
		DogBreedConfigureDTO dogBreedConfigureDTO = dogBreedConfigureDTOs.get(breedIndex);
		
		dogDTO = new DogDTO(userDTO, dogBreedConfigureDTO);
		dogJpaDAO.save(dogDTO);
		
		accountDTO.subCredit(MiscConfigure.DEFAULT_FIND_DOG_COST);
		accountJpaDAO.save(accountDTO);
		return dogDTO;
	}

}
