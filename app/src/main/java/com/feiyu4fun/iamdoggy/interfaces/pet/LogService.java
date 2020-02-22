package com.feiyu4fun.iamdoggy.interfaces.pet;

import com.feiyu4fun.iamdoggy.dtos.common.PetDTO;
import com.feiyu4fun.iamdoggy.dtos.doggy.ActivityLogDTO;

public interface LogService {
	ActivityLogDTO generateNewLog(PetDTO petDTO);
	ActivityLogDTO getTodayLog(PetDTO petDTO);
	void limitLatestLogs(ActivityLogDTO activityLogDTO, int limit);
	void appendLog(ActivityLogDTO activityLogDTO, String log);
}
