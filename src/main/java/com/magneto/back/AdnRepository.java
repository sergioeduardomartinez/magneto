package com.magneto.back;


import com.magneto.dto.Dna;

import java.util.List;

public interface AdnRepository {

    List<Dna> statistics();
    void insertAdn(Dna dna);
}
