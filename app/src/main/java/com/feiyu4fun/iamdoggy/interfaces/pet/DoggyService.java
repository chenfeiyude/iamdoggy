package com.feiyu4fun.iamdoggy.interfaces.pet;

import java.util.List;

import com.feiyu4fun.iamdoggy.dtos.doggy.DogDTO;
import com.feiyu4fun.iamdoggy.dtos.management.UserDTO;

public interface DoggyService {
	DogDTO getDog(UserDTO userDTO, long pid);
	List<DogDTO> getDogs(UserDTO userDTO);
	DogDTO getPrimaryDog(UserDTO userDTO);
}
