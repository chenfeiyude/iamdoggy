package com.feiyu4fun.iamdoggy.interfaces.daos.doggy;

import com.feiyu4fun.iamdoggy.dtos.doggy.ActivityLogDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("activityLogJpaDAO")
public interface ActivityLogJpaDAO extends JpaRepository<ActivityLogDTO, Long> {
	ActivityLogDTO findFirstByPidOrderByIdDesc(long pid);
}
