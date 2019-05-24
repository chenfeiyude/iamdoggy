package com.iamdoggy.iamdoggy.dtos.doggy;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import com.iamdoggy.iamdoggy.dtos.common.PetRelatedDTO;

@Entity
@Table(name ="activity_log")
@Data
public class ActivityLogDTO extends PetRelatedDTO {
	private LocalDate created = LocalDate.now();
	private String log = "";
	public void append(String log) {
		StringBuilder sb = new StringBuilder(this.log);
		sb.append(LocalDateTime.now());
		sb.append(log);
		sb.append("\n");
		this.log = sb.toString();
	}
}
