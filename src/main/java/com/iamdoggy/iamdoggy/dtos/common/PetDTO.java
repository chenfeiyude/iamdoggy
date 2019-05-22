package com.iamdoggy.iamdoggy.dtos.common;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import com.iamdoggy.iamdoggy.enums.PetHealthyState;
import com.iamdoggy.iamdoggy.enums.PetState;

@MappedSuperclass
public class PetDTO extends BaseDTO {
	protected String uid;
    protected int age = 0;
    protected PetState state = PetState.live; // dead, live
    protected PetHealthyState healthy = PetHealthyState.healthy; // healthy, ill
    protected LocalDateTime born = LocalDateTime.now();
    protected int cost;
    
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public PetState getState() {
		return state;
	}
	public void setState(PetState state) {
		this.state = state;
	}
	public PetHealthyState getHealthy() {
		return healthy;
	}
	public void setHealthy(PetHealthyState healthy) {
		this.healthy = healthy;
	}
	public LocalDateTime getBorn() {
		return born;
	}
	public void setBorn(LocalDateTime born) {
		this.born = born;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
}
