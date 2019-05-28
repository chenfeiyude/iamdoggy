package com.fytech.iamdoggy.services.pet;

import java.time.LocalDate;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fytech.iamdoggy.dtos.common.PetDTO;
import com.fytech.iamdoggy.dtos.doggy.ActivityLogDTO;
import com.fytech.iamdoggy.interfaces.daos.doggy.ActivityLogJpaDAO;
import com.fytech.iamdoggy.interfaces.pet.LogService;

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

}
