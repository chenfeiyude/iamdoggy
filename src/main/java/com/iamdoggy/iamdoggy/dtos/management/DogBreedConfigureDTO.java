package com.iamdoggy.iamdoggy.dtos.management;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.iamdoggy.iamdoggy.dtos.common.BaseDTO;

@Entity
@Table(name ="dog_breed_configure")
public class DogBreedConfigureDTO extends BaseDTO {
	private String breed; // corgi, husky
	private double rarity; // 0.01 - 1
	private int cost;
	
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
