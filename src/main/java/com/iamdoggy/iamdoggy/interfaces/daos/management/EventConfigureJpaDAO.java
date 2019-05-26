package com.iamdoggy.iamdoggy.interfaces.daos.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iamdoggy.iamdoggy.dtos.management.EventConfigureDTO;

@Repository("eventConfigureJpaDAO")
public interface EventConfigureJpaDAO extends JpaRepository<EventConfigureDTO, Long> {
	@Query("select sum(t.possibility) from EventConfigureDTO t")
    double getSumPossibility();
}