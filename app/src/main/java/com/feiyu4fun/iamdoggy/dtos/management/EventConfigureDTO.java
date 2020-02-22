package com.feiyu4fun.iamdoggy.dtos.management;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.feiyu4fun.iamdoggy.enums.EventType;
import lombok.Data;

import com.feiyu4fun.iamdoggy.dtos.common.BaseDTO;
import com.feiyu4fun.iamdoggy.interfaces.management.Possibililty;

@Entity
@Table(name ="event_configure")
@Data
public class EventConfigureDTO extends BaseDTO implements Possibililty {
	private EventType type;
	private String description; 
	private double possibility; // 0.01 - 1
	private boolean persist;
}
