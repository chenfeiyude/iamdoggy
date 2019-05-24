package com.iamdoggy.iamdoggy.dtos.doggy;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import com.iamdoggy.iamdoggy.dtos.common.PetRelatedDTO;

@Entity
@Table(name ="command")
@Data
public class CommandDTO extends PetRelatedDTO {
	private String command;
}
