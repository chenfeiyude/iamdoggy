package com.fytech.iamdoggy.interfaces.daos.doggy;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fytech.iamdoggy.dtos.doggy.DogDTO;
import com.fytech.iamdoggy.enums.PetState;

@Repository("dogJpaDAO")
public interface DogJpaDAO  extends JpaRepository<DogDTO, Long> {
	List<DogDTO> findAllByState(PetState state, Pageable pageable);
}
