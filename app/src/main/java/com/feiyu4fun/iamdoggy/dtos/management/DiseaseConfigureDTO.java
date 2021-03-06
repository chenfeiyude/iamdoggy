package com.feiyu4fun.iamdoggy.dtos.management;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import com.feiyu4fun.iamdoggy.dtos.common.BaseDTO;

@Entity
@Table(name ="disease_configure")
@Data
public class DiseaseConfigureDTO extends BaseDTO {
	private String type; // cold, cancer
	private String affect;
	private double possibility; // 0.1 - 1
}
