package com.example.inicial1.dtos;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class StatsResponse {

    private long countMutantDna;
    private long countHumanDna;
    private double ratio;
}
