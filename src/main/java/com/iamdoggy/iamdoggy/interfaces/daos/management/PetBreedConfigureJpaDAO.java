package com.iamdoggy.iamdoggy.interfaces.daos.management;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iamdoggy.iamdoggy.dtos.management.PetBreedConfigureDTO;
import com.iamdoggy.iamdoggy.enums.PetType;

@Repository("petBreedConfigureJpaDAO")
public interface PetBreedConfigureJpaDAO extends JpaRepository<PetBreedConfigureDTO, Long> {
	@Query("select sum(t.rarity) from PetBreedConfigureDTO t")
    double getSumRarity();

	List<PetBreedConfigureDTO> findAllByType(PetType type);
}
