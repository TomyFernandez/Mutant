package com.example.inicial1.controllers;

import com.example.inicial1.dtos.MutantRequest;
import com.example.inicial1.entities.Mutant;
import com.example.inicial1.repositories.MutantRepository;
import com.example.inicial1.services.MutantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mutant")
public class MutantController{

    private final MutantService mutantService;
    private final MutantRepository mutantRepository;
    public MutantController(MutantService mutantService, MutantRepository mutantRepository){
        this.mutantService = mutantService;
        this.mutantRepository = mutantRepository;
    }

    @PostMapping("/")
    public ResponseEntity<?> checkMutant(@RequestBody MutantRequest mutant1) {
        String[] dnaArray = mutant1.getDna();
         try {
             boolean isMutant = mutantService.isMutant(dnaArray);
             if (isMutant){
                 Mutant mutant = Mutant.builder()
                         .nombre(mutant1.getNombre())
                         .apellido(mutant1.getApellido())
                         .poder(mutant1.getPoder())
                         .dna(String.join(",", dnaArray))
                         .isMutant(true)
                         .build();
                 mutantRepository.saveIfNotExists(mutant);
                 return ResponseEntity.ok().build();
             } else {
                 return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
             }
         } catch (Exception e){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
         }

    }

}