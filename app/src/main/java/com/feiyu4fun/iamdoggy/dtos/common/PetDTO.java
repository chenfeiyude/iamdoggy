package com.feiyu4fun.iamdoggy.dtos.common;

import com.feiyu4fun.iamdoggy.enums.PetHealthyState;
import com.feiyu4fun.iamdoggy.enums.PetState;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.time.Period;

@MappedSuperclass
@Data
public class PetDTO extends BaseDTO {
	protected String uid;
	protected String name;
	@Transient
    protected String age;
    protected PetState state = PetState.live; // dead, live
    protected PetHealthyState healthy = PetHealthyState.healthy; // healthy, ill
    protected LocalDateTime born = LocalDateTime.now();
    protected int cost;
    protected boolean isPrimary = false;
    
    protected int level = 0;
    protected int speed;
    protected int attack;
    protected int defence;

    public String getAge() {
        if (age != null)
            return age;
        Period period = Period.between(LocalDateTime.now().toLocalDate(), born.toLocalDate());
        StringBuilder bob = new StringBuilder();
        if (period.getYears() > 0)
            bob.append(period.getYears()).append(" years ");
        if (period.getMonths() > 0)
            bob.append(period.getMonths()).append(" months ");
        if (period.getDays() > 0)
            bob.append(period.getDays()).append(" days ");
        age = bob.toString();
        return age;
    }
}
