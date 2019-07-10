package com.fytech.iamdoggy.dtos.common;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import lombok.Data;

import com.fytech.iamdoggy.enums.PetHealthyState;
import com.fytech.iamdoggy.enums.PetState;

@MappedSuperclass
@Data
public class PetDTO extends BaseDTO {
	protected String uid;
    protected int age = 0;
    protected PetState state = PetState.live; // dead, live
    protected PetHealthyState healthy = PetHealthyState.healthy; // healthy, ill
    protected LocalDateTime born = LocalDateTime.now();
    protected int cost;
    protected boolean isPrimary = false;
    
    protected int level = 0;
    protected int speed;
    protected int attack;
    protected int defence;
}
