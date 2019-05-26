package com.iamdoggy.iamdoggy.dtos.common;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import lombok.Data;

import com.iamdoggy.iamdoggy.enums.PetHealthyState;
import com.iamdoggy.iamdoggy.enums.PetState;

@MappedSuperclass
@Data
public class PetDTO extends BaseDTO {
	protected String uid;
    protected int age = 0;
    protected PetState state = PetState.live; // dead, live
    protected PetHealthyState healthy = PetHealthyState.healthy; // healthy, ill
    protected LocalDateTime born = LocalDateTime.now();
    protected int cost;
}
