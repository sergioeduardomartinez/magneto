package com.magneto.dto;

import java.lang.reflect.Array;

public class Dna {



    private Double count_mutant_dna;

    private Double count_human_dna;

    private Double ratio ;

    public Dna(Double ratio, Double count_mutant_dna, Double count_human_dna) {
        this.ratio = ratio;
        this.count_mutant_dna = count_mutant_dna;
        this.count_human_dna = count_human_dna;
    }

    public Dna() {

    }

    public Double getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public void setCount_mutant_dna(Double count_mutant_dna) {
        this.count_mutant_dna = count_mutant_dna;
    }

    public Double getCount_human_dna() {
        return count_human_dna;
    }

    public void setCount_human_dna(Double count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

}
