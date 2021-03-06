package com.feiyu4fun.iamdoggy.services.pet;

import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.feiyu4fun.iamdoggy.dtos.common.PetDTO;
import com.feiyu4fun.iamdoggy.dtos.doggy.ActivityLogDTO;
import com.feiyu4fun.iamdoggy.interfaces.daos.doggy.ActivityLogJpaDAO;
import com.feiyu4fun.iamdoggy.interfaces.pet.LogService;

@Service("logService")
@Slf4j
public class LogServiceImpl implements LogService {
	
	@Autowired
	private ActivityLogJpaDAO activityLogJpaDAO;

	@Override
	public void appendLog(ActivityLogDTO activityLogDTO, String log) {
		if (activityLogDTO == null || StringUtils.isEmpty(log)) {
			return;
		}
		activityLogDTO.append(log);
		activityLogJpaDAO.save(activityLogDTO);
	}

	@Override
	public ActivityLogDTO generateNewLog(PetDTO petDTO) {
		if (petDTO == null) {
			return null;
		}
		ActivityLogDTO activityLogDTO = new ActivityLogDTO();
		activityLogDTO.setPid(petDTO.getId());
		activityLogJpaDAO.save(activityLogDTO);
		return activityLogDTO;
	}

	@Override
	public ActivityLogDTO getTodayLog(PetDTO petDTO) {
		if (petDTO == null) {
			return null;
		}
		
		ActivityLogDTO activityLogDTO = activityLogJpaDAO.findFirstByPidOrderByIdDesc(petDTO.getId());
		if (activityLogDTO == null || !activityLogDTO.getCreated().isEqual(LocalDate.now())) {
			activityLogDTO = generateNewLog(petDTO);
		}
		return activityLogDTO;
	}

	@Override
	public void limitLatestLogs(ActivityLogDTO activityLogDTO, int limit) {
		if (activityLogDTO == null || limit <= 0) {
			return;
		}
		String[] logLines = activityLogDTO.getLog().split("\n");
		StringBuilder sBuilder = new StringBuilder();
		int end = Math.min(logLines.length, limit);
		for (int i = 0; i < end; i++) {
			sBuilder.append(logLines[i] + "\n");
		}
		activityLogDTO.setLog(sBuilder.toString());
	}

}
