package com.iamdoggy.iamdoggy.dtos.doggy;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import com.iamdoggy.iamdoggy.dtos.common.PetDTO;
import com.iamdoggy.iamdoggy.dtos.common.PetRelatedDTO;
import com.iamdoggy.iamdoggy.dtos.management.EventConfigureDTO;
import com.iamdoggy.iamdoggy.enums.EventLevel;
import com.iamdoggy.iamdoggy.enums.EventType;

@Entity
@Table(name = "event")
@Data
public class EventDTO extends PetRelatedDTO {
	private EventType type;
	private EventLevel level= EventLevel.low;
	private LocalDateTime created = LocalDateTime.now();
	private String description;
	
	public EventDTO() {}
	
	public EventDTO(PetDTO petDTO, EventConfigureDTO eventConfigureDTO) {
		type = eventConfigureDTO.getType();
		description = eventConfigureDTO.getDescription();
		pid = petDTO.getId();
	}
	
	@Override
	public String toString() {
		return type.toString() + " " + level + ": " + description;
	}
}
