package com.feiyu4fun.iamdoggy.dtos.common;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import com.feiyu4fun.iamdoggy.enums.PetHealthyState;
import com.feiyu4fun.iamdoggy.enums.PetState;
import lombok.Data;

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
