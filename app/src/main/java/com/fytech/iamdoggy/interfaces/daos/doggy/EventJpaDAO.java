package com.fytech.iamdoggy.interfaces.daos.doggy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fytech.iamdoggy.dtos.doggy.EventDTO;

@Repository("eventJpaDAO")
public interface EventJpaDAO extends JpaRepository<EventDTO, Long> {

}
