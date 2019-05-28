package com.fytech.iamdoggy.interfaces.pet;

import com.fytech.iamdoggy.dtos.common.PetDTO;
import com.fytech.iamdoggy.dtos.doggy.ActivityLogDTO;

public interface LogService {
	ActivityLogDTO generateNewLog(PetDTO petDTO);
	ActivityLogDTO getTodayLog(PetDTO petDTO);
	void appendLog(ActivityLogDTO activityLogDTO, String log);
}
