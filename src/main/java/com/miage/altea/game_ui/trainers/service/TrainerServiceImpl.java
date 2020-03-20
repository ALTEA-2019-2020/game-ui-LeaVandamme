package com.miage.altea.game_ui.trainers.service;

import com.miage.altea.game_ui.pokemonTypes.bo.Pokemon;
import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import com.miage.altea.game_ui.trainers.service.TrainerService;
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
    private String pokemonServiceUrl;
    private PokemonTypeService pokemonTypeService;

    public List<Trainer> listTrainer() {
        Trainer[] tList =  restTemplate.getForObject(pokemonServiceUrl+"trainers/", Trainer[].class);
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
        return restTemplate.getForObject(pokemonServiceUrl+"trainers/"+name+"/", Trainer.class);
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    void setTrainerServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }

    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }
}
