package com.iamdoggy.iamdoggy.dtos.management;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.iamdoggy.iamdoggy.configurations.MiscConfigure;
import com.iamdoggy.iamdoggy.dtos.common.BaseDTO;

@Entity
@Table(name ="account")
public class AccountDTO extends BaseDTO {
	private String uid;
	private int credit = MiscConfigure.DEFAULT_CREDIT;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	
}
