package com.feiyu4fun.iamdoggy.interfaces.daos.doggy;

import com.feiyu4fun.iamdoggy.dtos.doggy.EventDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("eventJpaDAO")
public interface EventJpaDAO extends JpaRepository<EventDTO, Long> {

}
