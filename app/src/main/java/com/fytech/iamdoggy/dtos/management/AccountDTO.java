package com.fytech.iamdoggy.dtos.management;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import com.fytech.iamdoggy.configurations.MiscConfigure;
import com.fytech.iamdoggy.dtos.common.BaseDTO;

@Entity
@Table(name ="account")
@Data
public class AccountDTO extends BaseDTO {
	private String uid;
	private int credit = MiscConfigure.DEFAULT_CREDIT;
	
	public void subCredit(int subCredit) {
		this.credit -= subCredit;
	}
	
}
