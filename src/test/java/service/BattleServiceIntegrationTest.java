package service;

import com.miage.altea.game_ui.battle.bo.Battle;
import com.miage.altea.game_ui.battle.bo.BattleTrainer;
import com.miage.altea.game_ui.battle.service.BattleService;
import com.miage.altea.game_ui.battle.service.BattleServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BattleServiceIntegrationTest {

    @Test
    void getBattles_shouldReturnAllBattles() {
        String url = "http://localhost:8082";
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        BattleService battleService = new BattleServiceImpl();
        battleService.setRestTemplate(restTemplate);
        battleService.setBattleServiceUrl(url);

        Battle battle = new Battle();
        BattleTrainer battleTrainer = new BattleTrainer("Ash", true);
        battle.setTrainer(battleTrainer);
        Battle[] battles = {battle};

        String expectedUrl = "http://localhost:8082/battles/";
        Mockito.when(restTemplate.getForObject(expectedUrl, Battle[].class)).thenReturn(battles);

        Iterable<Battle> res = battleService.getBattles();
        assertNotNull(battles);
        assertEquals(1, battles.length);

        assertEquals("Ash", battles[0].getTrainer().getName());
    }

    @Test
    void getCurrentBattle_shouldReturnABattle() {

        String url = "http://localhost:8082";
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        BattleService battleService = new BattleServiceImpl();
        battleService.setRestTemplate(restTemplate);
        battleService.setBattleServiceUrl(url);

        Battle battle = new Battle();
        BattleTrainer battleTrainer = new BattleTrainer("Ash", true);
        battle.setTrainer(battleTrainer);
        battle.setUuid(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));

        String expectedUrl = "http://localhost:8082/battles/";
        Mockito.when(restTemplate.getForObject(expectedUrl+battle.getUuid().toString()+"/", Battle.class)).thenReturn(battle);

        Battle res = battleService.getCurrentBattle(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));
        assertNotNull(res);

        assertEquals("Ash", battle.getTrainer().getName());
    }

    @Test
    void attackAction_shouldReturnTheBattle() throws Exception {

        String url = "http://localhost:8082";
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        BattleService battleService = new BattleServiceImpl();
        battleService.setRestTemplate(restTemplate);
        battleService.setBattleServiceUrl(url);

        Battle battle = new Battle();
        BattleTrainer battleTrainer = new BattleTrainer("Ash", true);
        BattleTrainer battleOpponent = new BattleTrainer("Misty", false);
        battle.setTrainer(battleTrainer);
        battle.setOpponent(battleOpponent);
        battle.setUuid(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"));

        String expectedUrl = "http://localhost:8082/battles/";
        Mockito.when(restTemplate.getForObject(expectedUrl+battle.getUuid()+"/"
                + battleTrainer.getName() + "/" + "/attack", Battle.class)).thenReturn(battle);

        Battle res = battleService.attackAction(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), "Ash", battle);
        assertNotNull(res);

        assertEquals("Ash", res.getTrainer().getName());
    }

}
