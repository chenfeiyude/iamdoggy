package com.iamdoggy.iamdoggy.interfaces.pet;

import com.iamdoggy.iamdoggy.dtos.common.PetDTO;
import com.iamdoggy.iamdoggy.dtos.doggy.ActivityLogDTO;

public interface LogService {
	ActivityLogDTO generateNewLog(PetDTO petDTO);
	void appendLog(ActivityLogDTO activityLogDTO, String log);
}
