package com.fytech.iamdoggy.interfaces.pet;

import com.fytech.iamdoggy.dtos.doggy.DogDTO;
import com.fytech.iamdoggy.dtos.management.UserDTO;

public interface DoggyService {
	DogDTO getDog(UserDTO userDTO, long pid);
}
