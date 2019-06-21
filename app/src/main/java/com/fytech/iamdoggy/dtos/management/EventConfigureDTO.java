package com.fytech.iamdoggy.dtos.management;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import com.fytech.iamdoggy.dtos.common.BaseDTO;
import com.fytech.iamdoggy.enums.EventType;
import com.fytech.iamdoggy.interfaces.management.Possibililty;

@Entity
@Table(name ="event_configure")
@Data
public class EventConfigureDTO extends BaseDTO implements Possibililty {
	private EventType type; 
	private String description; 
	private double possibility; // 0.01 - 1
	private boolean persist;
}
