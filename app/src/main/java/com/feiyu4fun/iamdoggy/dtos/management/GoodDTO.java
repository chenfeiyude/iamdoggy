package com.feiyu4fun.iamdoggy.dtos.management;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import com.feiyu4fun.iamdoggy.dtos.common.BaseDTO;

@Entity
@Table(name ="good")
@Data
public class GoodDTO extends BaseDTO {
	protected String uid;
	protected String name;

}
