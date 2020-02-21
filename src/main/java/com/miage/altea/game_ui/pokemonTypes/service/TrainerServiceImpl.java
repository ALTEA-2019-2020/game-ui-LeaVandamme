package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.pokemonTypes.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService{

    private RestTemplate restTemplate;
    private String pokemonServiceUrl;

    public List<Trainer> listTrainer() {
        return Arrays.asList(restTemplate.getForObject(pokemonServiceUrl+"trainers/", Trainer[].class));
    }

    @Override
    public Trainer getTrainerByName(String name) {
        return restTemplate.getForObject(pokemonServiceUrl+"trainers/"+name+"/", Trainer.class);
    }

    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    void setTrainerServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}
