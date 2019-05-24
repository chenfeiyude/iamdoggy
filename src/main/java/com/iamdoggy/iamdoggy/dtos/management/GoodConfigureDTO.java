package com.iamdoggy.iamdoggy.dtos.management;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import com.iamdoggy.iamdoggy.dtos.common.BaseDTO;

@Entity
@Table(name ="good_configure")
@Data
public class GoodConfigureDTO extends BaseDTO {
	private String name;
	private int cost;
	private String type; // medicine, food, toy, bed, water
}
