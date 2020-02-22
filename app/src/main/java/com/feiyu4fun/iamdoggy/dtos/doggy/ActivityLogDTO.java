package com.feiyu4fun.iamdoggy.dtos.doggy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.feiyu4fun.iamdoggy.dtos.common.PetRelatedDTO;
import org.springframework.util.StringUtils;

import lombok.Data;

@Entity
@Table(name ="activity_log")
@Data
public class ActivityLogDTO extends PetRelatedDTO {
	private LocalDate created = LocalDate.now();
	@Lob
	private String log = "";
	public void append(String log) {
		if (StringUtils.isEmpty(log)) {
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
		sb.append("  ");
		sb.append(log);
		sb.append("\n");
		sb.append(this.log);
		this.log = sb.toString();
	}
}
