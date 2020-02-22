package com.feiyu4fun.iamdoggy.dtos.management;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import com.feiyu4fun.iamdoggy.dtos.common.BaseDTO;

@Entity
@Table(name ="command_configure")
@Data
public class CommandConfigureDTO extends BaseDTO {
	private String command;
	private double difficulty;
	private String activity;
}
