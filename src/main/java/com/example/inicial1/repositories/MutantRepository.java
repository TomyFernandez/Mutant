package com.example.inicial1.repositories;

import com.example.inicial1.entities.Mutant;

import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MutantRepository extends org.springframework.data.jpa.repository.JpaRepository<Mutant, Long> {
        Optional<Mutant> findByDna(String dnaSequence);

        long countByIsMutant(boolean isMutant);

}