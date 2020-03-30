package com.miage.altea.game_ui.trainers.service;

import com.miage.altea.game_ui.pokemonTypes.bo.Pokemon;
import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private RestTemplate restTemplate;
    private String trainerServiceUrl;
    private PokemonTypeService pokemonTypeService;

    @Override
    public List<Trainer> listTrainer() {
        Trainer[] tList =  restTemplate.getForObject(trainerServiceUrl+"/trainers/", Trainer[].class);
        List<Trainer> trainersList = Arrays.asList(tList);
        for(Trainer t : tList){
            for(Pokemon pok : t.getTeam()){
                pok.setPt(pokemonTypeService.getPokemonType(pok.getPokemonTypeId()));
            }
        }
        return trainersList;
    }

    @Override
    public Trainer getTrainerByName(String name) {
        Trainer t = restTemplate.getForObject(trainerServiceUrl+"/trainers/"+name+"/", Trainer.class);
        for(Pokemon pok : t.getTeam()){
            pok.setPt(pokemonTypeService.getPokemonType(pok.getPokemonTypeId()));
        }
        return t;
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    public void setTrainerServiceUrl(String trainerServiceUrl) {
        this.trainerServiceUrl =trainerServiceUrl;
    }

    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }
}
