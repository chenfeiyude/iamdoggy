package com.feiyu4fun.iamdoggy.dtos.doggy;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.feiyu4fun.iamdoggy.dtos.common.PetRelatedDTO;
import lombok.Data;

@Entity
@Table(name ="command")
@Data
public class CommandDTO extends PetRelatedDTO {
	private String command;
}
