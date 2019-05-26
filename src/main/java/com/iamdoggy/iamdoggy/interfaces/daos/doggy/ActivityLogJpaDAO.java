package com.iamdoggy.iamdoggy.interfaces.daos.doggy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamdoggy.iamdoggy.dtos.doggy.ActivityLogDTO;

@Repository("activityLogJpaDAO")
public interface ActivityLogJpaDAO extends JpaRepository<ActivityLogDTO, Long> {
	ActivityLogDTO findFirstByPidOrderById(long pid);
}
