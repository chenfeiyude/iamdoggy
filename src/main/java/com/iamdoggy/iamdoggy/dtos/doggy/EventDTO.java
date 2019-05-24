package com.iamdoggy.iamdoggy.dtos.doggy;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import com.iamdoggy.iamdoggy.dtos.common.PetRelatedDTO;
import com.iamdoggy.iamdoggy.enums.EventLevel;
import com.iamdoggy.iamdoggy.enums.EventType;

@Entity
@Table(name = "event")
@Data
public class EventDTO extends PetRelatedDTO {
	private EventType type;
	private EventLevel level;
}
