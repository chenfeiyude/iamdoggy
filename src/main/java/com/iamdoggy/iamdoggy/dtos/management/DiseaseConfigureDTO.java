package com.iamdoggy.iamdoggy.dtos.management;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.iamdoggy.iamdoggy.dtos.common.BaseDTO;

@Entity
@Table(name ="disease_configure")
public class DiseaseConfigureDTO extends BaseDTO {
	private String type; // cold, cancer
	private String affect;
	private double recoveryRate; // 0.1 - 1
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAffect() {
		return affect;
	}
	public void setAffect(String affect) {
		this.affect = affect;
	}
	public double getRecoveryRate() {
		return recoveryRate;
	}
	public void setRecoveryRate(double recoveryRate) {
		this.recoveryRate = recoveryRate;
	}
	
}
