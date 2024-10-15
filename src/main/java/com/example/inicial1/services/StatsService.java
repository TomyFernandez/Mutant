package com.example.inicial1.services;

import com.example.inicial1.dtos.StatsResponse;
import com.example.inicial1.repositories.MutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private final MutantRepository mutantRepository;

    @Autowired
    public StatsService(MutantRepository mutantRepository){
        this.mutantRepository = mutantRepository;
    }

    public StatsResponse getStats() {
        long countMutantDna = mutantRepository.countByIsMutant(true);
        long countHumanDna = mutantRepository.countByIsMutant(false);
        double ratio = countHumanDna == 0 ? 0 : (double) countMutantDna / countHumanDna;

        return StatsResponse.builder()
                .countMutantDna(countMutantDna)
                .countHumanDna(countHumanDna)
                .ratio(ratio)
                .build();
    }

}
