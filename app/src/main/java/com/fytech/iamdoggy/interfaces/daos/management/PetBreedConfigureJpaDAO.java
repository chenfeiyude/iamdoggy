package com.fytech.iamdoggy.interfaces.daos.management;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fytech.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.fytech.iamdoggy.enums.PetType;

@Repository("petBreedConfigureJpaDAO")
public interface PetBreedConfigureJpaDAO extends JpaRepository<PetBreedConfigureDTO, Long> {
	List<PetBreedConfigureDTO> findAllByType(PetType type);
}
