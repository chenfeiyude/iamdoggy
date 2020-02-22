package com.feiyu4fun.iamdoggy.dtos.management;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.feiyu4fun.iamdoggy.enums.GoodType;
import lombok.Data;

import com.feiyu4fun.iamdoggy.dtos.common.BaseDTO;

@Entity
@Table(name ="good_configure")
@Data
public class GoodConfigureDTO extends BaseDTO {
	private String name;
	private int cost;
	private GoodType type;
}
