package com.iamdoggy.iamdoggy.services.pet;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamdoggy.iamdoggy.dtos.common.PetDTO;
import com.iamdoggy.iamdoggy.dtos.doggy.ActivityLogDTO;
import com.iamdoggy.iamdoggy.interfaces.daos.doggy.ActivityLogJpaDAO;
import com.iamdoggy.iamdoggy.interfaces.pet.LogService;

@Service("logService")
public class LogServiceImpl implements LogService {
	
	@Autowired
	private ActivityLogJpaDAO activityLogJpaDAO;

	@Override
	public void appendLog(ActivityLogDTO activityLogDTO, String log) {
		activityLogDTO.append(log);
		activityLogJpaDAO.save(activityLogDTO);
	}

	@Override
	public ActivityLogDTO generateNewLog(PetDTO petDTO) {
		ActivityLogDTO activityLogDTO = new ActivityLogDTO();
		activityLogDTO.setPid(petDTO.getId());
		activityLogJpaDAO.save(activityLogDTO);
		return activityLogDTO;
	}

	@Override
	public ActivityLogDTO getTodayLog(PetDTO petDTO) {
		ActivityLogDTO activityLogDTO = activityLogJpaDAO.findFirstByPidOrderById(petDTO.getId());
		if (activityLogDTO == null || !activityLogDTO.getCreated().isEqual(LocalDate.now()) ) {
			activityLogDTO = generateNewLog(petDTO);
		}
		return activityLogDTO;
	}

}
