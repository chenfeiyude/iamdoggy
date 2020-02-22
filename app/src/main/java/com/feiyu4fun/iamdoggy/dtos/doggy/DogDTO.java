package com.feiyu4fun.iamdoggy.dtos.doggy;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.feiyu4fun.iamdoggy.dtos.common.PetDTO;
import com.feiyu4fun.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.feiyu4fun.iamdoggy.dtos.management.UserDTO;
import lombok.Data;

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
