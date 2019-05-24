package com.iamdoggy.iamdoggy.dtos.doggy;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import com.iamdoggy.iamdoggy.dtos.common.PetRelatedDTO;

@Entity
@Table(name = "event")
@Data
public class EventDTO extends PetRelatedDTO {
	private String type;
}
