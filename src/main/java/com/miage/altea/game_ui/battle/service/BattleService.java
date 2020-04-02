package com.miage.altea.game_ui.battle.service;

import com.miage.altea.game_ui.battle.bo.Battle;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

public interface BattleService {
    Battle getCurrentBattle(UUID uuid);
    List<Battle> getBattles();
    Battle attackAction(UUID uuid, String attacker, Battle battle) throws Exception;
    void setRestTemplate(RestTemplate restTemplate);
    void setBattleServiceUrl(String pokemonServiceUrl);
}
