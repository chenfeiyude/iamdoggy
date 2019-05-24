package com.iamdoggy.iamdoggy.dtos.doggy;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import com.iamdoggy.iamdoggy.dtos.common.PetDTO;
import com.iamdoggy.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.iamdoggy.iamdoggy.dtos.management.UserDTO;

@Entity
@Table(name ="dog")
@Data
public class DogDTO extends PetDTO {
	private String breed; // linked to breed configure
	
	public DogDTO() {}
	
	public DogDTO(UserDTO userDTO, PetBreedConfigureDTO dogBreedConfigureDTO) {
		uid = userDTO.getUid();
		breed = dogBreedConfigureDTO.getBreed();
	}
}
