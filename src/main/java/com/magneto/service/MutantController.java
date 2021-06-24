package com.magneto.service;


import com.magneto.back.AdnRepository;
import com.magneto.back.AdnRepositoryImpl;
import com.magneto.dto.Dna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import static java.lang.Math.min;
import static java.lang.Math.max;
@RestController
public class MutantController {
    @Autowired
    AdnRepository adnRepository;
    String matriz [][];

    @PostMapping(path="/mutant" , consumes = "application/json")
    @ResponseBody
    public boolean isMutant(@RequestBody Map<String, Object> payload, HttpServletResponse response) throws IOException, InterruptedException {
        ArrayList value;
        String keys;
        char prueba2 ;
        String okAdnHz = " ";
        int noEntra =0;
        String adn = "";
        String adnV = "";
        String adnD = "";
        String temp = "";
        int count = 0;
        int count2  = 0;
        ArrayList<String> lista = new ArrayList<String>();

        value  = (ArrayList) payload.get("dna");

        String matriz [][] = new String [value.size()] [value.get(0).toString().length()];
        /* construir tabla de adns in*/
        for(int f=0; f < value.size(); f++){
            String test = (String) value.get(f);
            for(int c=0; c < test.length(); c++){
                matriz[f][c] = String.valueOf(test.charAt(c));
            }
        }
        /* construir tabla de adns fin*/
        /*Recorrer matriz ini*/
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                /* validación si es mutante horizontal ini*/
                if(noEntra != 1){
                    if (j<4){
                        if(okAdnHz.equals(matriz[i][j])){
                                adn = matriz[i][j];
                            if(j==3){
                                    lista.add(adn);
                                }
                        }else{
                            okAdnHz = matriz[i][j];
                            if(j==2){
                                noEntra = 1;
                                adn = " ";
                            }
                        }
                    }
                }
                /* validación si es mutante horizontal fin*/
                //encontrar coincidencias verticales ini */
                if(adnV.equals(matriz[j][i])){
                    adnV = matriz[j][i];
                    count2 ++;
                    if(count2 == 3){
                        lista.add(adnV);
                    }
                }else{
                    adnV = matriz[j][i];
                }
                //encontrar coincidencias verticales fin */
            }
            count2 = 0;
            noEntra = 0;
            okAdnHz = " ";
        }
        /*Recorrer matriz fin*/

        /*Recorrer matriz diagonales ini*/
        for (int i = 1 - value.size(); i < value.get(0).toString().length(); i++){
            for (int x = -min(0, i), y = max(0, i); x < value.size() && y < value.get(0).toString().length(); x++, y++) {
                if(adnD.equals(matriz[y][x])){
                    adnD = matriz[y][x];
                    count ++;
                    if(count == 3){
                        lista.add(adnD);
                    }
                }else{
                    adnD = matriz[y][x];
                }
            }
            count = 0;
        }
        /*Recorrer matriz diagonales fin*/
        System.out.print("Letras coincidencias -->" + lista);

        /* respuesta 200 si en la lista existe mas de una coincidencia ini*/
        if(lista.size() > 1 ){
            Dna dna = new Dna();
            dna.setCount_mutant_dna(1.0);
            dna.setCount_human_dna(0.0);
            //insertar en bd ini
            adnRepository.insertAdn(dna);
            response.setStatus(200);
            return true;
        }else{
            Dna dna = new Dna();
            dna.setCount_mutant_dna(0.0);
            dna.setCount_human_dna(1.0);
            //insertar en bd ini
            adnRepository.insertAdn(dna );
             response.setStatus(403);
            return false;

        }
        /* respuesta 200 si en la lista existe mas de una coincidencia fin*/
    }


    @GetMapping(path="/stats")
    public List<Dna> getAllProovedor(){

        return adnRepository.statistics();
    }
}
