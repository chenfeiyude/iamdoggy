package com.iamdoggy.iamdoggy.interfaces.daos.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iamdoggy.iamdoggy.dtos.management.DogBreedConfigureDTO;

@Repository("dogBreedConfigureJpaDAO")
public interface DogBreedConfigureJpaDAO extends JpaRepository<DogBreedConfigureDTO, Long> {
	@Query("select sum(t.rarity) from DogBreedConfigureDTO t")
    double getSumRarity();
}
