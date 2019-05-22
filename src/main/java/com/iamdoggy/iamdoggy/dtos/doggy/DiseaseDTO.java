package com.iamdoggy.iamdoggy.dtos.doggy;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.iamdoggy.iamdoggy.dtos.common.PetRelatedDTO;

@Entity
@Table(name ="disease")
public class DiseaseDTO extends PetRelatedDTO {
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	} 
	
}
