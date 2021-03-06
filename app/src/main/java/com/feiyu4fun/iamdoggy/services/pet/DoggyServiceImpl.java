package com.feiyu4fun.iamdoggy.services.pet;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feiyu4fun.iamdoggy.dtos.doggy.DogDTO;
import com.feiyu4fun.iamdoggy.dtos.management.UserDTO;
import com.feiyu4fun.iamdoggy.interfaces.daos.doggy.DogJpaDAO;
import com.feiyu4fun.iamdoggy.interfaces.pet.DoggyService;

@Service("doggyService")
public class DoggyServiceImpl implements DoggyService {
	@Autowired
	private DogJpaDAO dogJpaDAO;

	@Override
	public DogDTO getDog(UserDTO userDTO, long pid) {
		if (userDTO != null) {
			Optional<DogDTO> dogDTO = dogJpaDAO.findById(pid);
			if (dogDTO.isPresent() && dogDTO.get().getUid().equals(userDTO.getUid())) {
				return dogDTO.get();
			}
		}
		return null;
	}

	@Override
	public List<DogDTO> getDogs(UserDTO userDTO) {
		List<DogDTO> dogDTOs = Collections.emptyList();
		if (userDTO != null) {
			dogDTOs = dogJpaDAO.findAllByUid(userDTO.getUid());
		}
		return dogDTOs;
	}

	@Override
	public DogDTO getPrimaryDog(UserDTO userDTO) {
		if (userDTO != null) {
			DogDTO dogDTO = dogJpaDAO.findByUidAndIsPrimary(userDTO.getUid(), true);
			return dogDTO;
		}
		return null;
	}
	
	
}
