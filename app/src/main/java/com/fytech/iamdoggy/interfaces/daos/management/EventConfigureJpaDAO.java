package com.fytech.iamdoggy.interfaces.daos.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fytech.iamdoggy.dtos.management.EventConfigureDTO;

@Repository("eventConfigureJpaDAO")
public interface EventConfigureJpaDAO extends JpaRepository<EventConfigureDTO, Long> {
}
