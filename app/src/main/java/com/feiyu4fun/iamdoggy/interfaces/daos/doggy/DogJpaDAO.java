package com.feiyu4fun.iamdoggy.interfaces.daos.doggy;

import java.util.List;

import com.feiyu4fun.iamdoggy.dtos.doggy.DogDTO;
import com.feiyu4fun.iamdoggy.enums.PetState;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("dogJpaDAO")
public interface DogJpaDAO  extends JpaRepository<DogDTO, Long> {
	List<DogDTO> findAllByState(PetState state, Pageable pageable);
	List<DogDTO> findAllByUid(String uid);
	DogDTO findFirstByUid(String uid);
	DogDTO findByUidAndIsPrimary(String uid, boolean isPrimary);
}
