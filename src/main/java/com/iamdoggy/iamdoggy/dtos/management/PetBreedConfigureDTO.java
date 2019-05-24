package com.iamdoggy.iamdoggy.dtos.management;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import com.iamdoggy.iamdoggy.dtos.common.BaseDTO;
import com.iamdoggy.iamdoggy.enums.PetType;

@Entity
@Table(name ="pet_breed_configure")
@Data
public class PetBreedConfigureDTO extends BaseDTO {
	private PetType type; // dog, cat, ..
	private String breed; // corgi, husky, ..
	private double rarity; // 0.01 - 1
	private int cost;

}
