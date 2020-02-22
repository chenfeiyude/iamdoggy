package com.feiyu4fun.iamdoggy.interfaces.daos.management;

import java.util.List;

import com.feiyu4fun.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.feiyu4fun.iamdoggy.enums.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("petBreedConfigureJpaDAO")
public interface PetBreedConfigureJpaDAO extends JpaRepository<PetBreedConfigureDTO, Long> {
	List<PetBreedConfigureDTO> findAllByType(PetType type);
}
