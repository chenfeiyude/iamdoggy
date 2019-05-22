package com.iamdoggy.iamdoggy.dtos.doggy;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.iamdoggy.iamdoggy.dtos.common.PetDTO;
import com.iamdoggy.iamdoggy.dtos.management.DogBreedConfigureDTO;
import com.iamdoggy.iamdoggy.dtos.management.UserDTO;

@Entity
@Table(name ="dog")
public class DogDTO extends PetDTO {
	private String breed; // linked to breed configure
	
	public DogDTO() {}
	
	public DogDTO(UserDTO userDTO, DogBreedConfigureDTO dogBreedConfigureDTO) {
		uid = userDTO.getUid();
		breed = dogBreedConfigureDTO.getBreed();
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}
	
}
