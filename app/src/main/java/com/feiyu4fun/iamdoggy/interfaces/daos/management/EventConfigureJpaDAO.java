package com.feiyu4fun.iamdoggy.interfaces.daos.management;

import com.feiyu4fun.iamdoggy.dtos.management.EventConfigureDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("eventConfigureJpaDAO")
public interface EventConfigureJpaDAO extends JpaRepository<EventConfigureDTO, Long> {
}
