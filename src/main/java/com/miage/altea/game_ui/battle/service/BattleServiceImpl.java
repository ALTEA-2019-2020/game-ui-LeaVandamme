package com.miage.altea.game_ui.battle.service;

import com.miage.altea.game_ui.battle.bo.Battle;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class BattleServiceImpl implements BattleService{

    private RestTemplate restTemplate;
    private String pokemonServiceUrl;

    @Override
    public Battle getCurrentBattle(UUID uuid) {
        return restTemplate.getForObject(pokemonServiceUrl+"battles/"+uuid.toString()+"/", Battle.class);
    }

    @Override
    public List<Battle> getBattles() {
        return Arrays.asList(restTemplate.getForObject(pokemonServiceUrl+"battles/", Battle[].class));

    }

    @Override
    public Battle attackAction(UUID uuid, String attacker, Battle battle) throws Exception {
        return restTemplate.getForObject(pokemonServiceUrl+"battles/"+uuid.toString()+"/"
                + attacker + "/" + "/attack", Battle.class);

    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${battle.service.url}")
    void setTrainerServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}
