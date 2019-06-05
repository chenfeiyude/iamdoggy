package com.fytech.iamdoggy.services.pet;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fytech.iamdoggy.dtos.doggy.DogDTO;
import com.fytech.iamdoggy.dtos.management.UserDTO;
import com.fytech.iamdoggy.interfaces.daos.doggy.DogJpaDAO;
import com.fytech.iamdoggy.interfaces.pet.DoggyService;

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
	
	
}
