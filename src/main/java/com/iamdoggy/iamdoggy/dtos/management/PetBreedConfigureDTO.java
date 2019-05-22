package com.iamdoggy.iamdoggy.dtos.management;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.iamdoggy.iamdoggy.dtos.common.BaseDTO;
import com.iamdoggy.iamdoggy.enums.PetType;

@Entity
@Table(name ="pet_breed_configure")
public class PetBreedConfigureDTO extends BaseDTO {
	private PetType type; // dog, cat, ..
	private String breed; // corgi, husky, ..
	private double rarity; // 0.01 - 1
	private int cost;
	
	public PetType getType() {
		return type;
	}
	public void setType(PetType type) {
		this.type = type;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public double getRarity() {
		return rarity;
	}
	public void setRarity(double rarity) {
		this.rarity = rarity;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
}
