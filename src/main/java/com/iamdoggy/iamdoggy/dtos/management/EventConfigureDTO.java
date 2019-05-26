package com.iamdoggy.iamdoggy.dtos.management;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import com.iamdoggy.iamdoggy.dtos.common.BaseDTO;
import com.iamdoggy.iamdoggy.enums.EventType;

@Entity
@Table(name ="event_configure")
@Data
public class EventConfigureDTO extends BaseDTO {
	private EventType type; 
	private String description; 
	private double possibility; // 0.01 - 1

}
