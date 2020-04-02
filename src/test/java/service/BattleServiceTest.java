package service;

import com.miage.altea.game_ui.battle.bo.Battle;
import com.miage.altea.game_ui.battle.bo.BattleTrainer;
import com.miage.altea.game_ui.battle.service.BattleService;
import com.miage.altea.game_ui.battle.service.BattleServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BattleServiceTest {

    @Test
    void setBattleUrl_shouldBeAnnotatedWithValue() throws NoSuchMethodException {
        var setter = BattleServiceImpl.class.getDeclaredMethod("setBattleServiceUrl", String.class);
        var valueAnnotation = setter.getAnnotation(Value.class);
        assertNotNull(valueAnnotation);
        assertEquals("${battle.service.url}", valueAnnotation.value());
    }

    @Test
    void getBattles_shouldCallTheRemoteService() {
        var url = "http://localhost:8082";

        var restTemplate = mock(RestTemplate.class);
        var battleServiceImpl = new BattleServiceImpl();
        battleServiceImpl.setRestTemplate(restTemplate);
        battleServiceImpl.setBattleServiceUrl(url);

        var battleTrainer = new BattleTrainer("ash", true);
        var battle = new Battle();
        battle.setTrainer(battleTrainer);

        var expectedUrl = "http://localhost:8082/battles/";
        Mockito.when(restTemplate.getForObject(expectedUrl, Battle[].class)).thenReturn(new Battle[]{battle});
        var battles = battleServiceImpl.getBattles();
        assertNotNull(battles);
        assertEquals(1, battles.size());

        verify(restTemplate).getForObject(expectedUrl, Battle[].class);
    }

    @Test
    void getBattle_withUUID_shouldRemoteTheService() {
        String url = "http://localhost:8082";
        var restTemplate = mock(RestTemplate.class);
        BattleService battleService = new BattleServiceImpl();
        battleService.setRestTemplate(restTemplate);
        battleService.setBattleServiceUrl(url);

        Battle battle = new Battle();
        BattleTrainer battleTrainer = new BattleTrainer("Ash",true);
        battle.setTrainer(battleTrainer);
        battle.setUuid(UUID.randomUUID());

        var expectedUrl = "http://localhost:8082/battles/";
        Mockito.when(restTemplate.getForObject(expectedUrl+battle.getUuid().toString()+"/", Battle.class)).thenReturn(battle);

        Battle battleRes = battleService.getCurrentBattle(battle.getUuid());
        assertEquals(battleTrainer, battleRes.getTrainer());
    }

    @Test
    void pokemonServiceImpl_shouldBeAnnotatedWithService(){
        assertNotNull(BattleServiceImpl.class.getAnnotation(Service.class));
    }

    @Test
    void setRestTemplate_shouldBeAnnotatedWithAutowired() throws NoSuchMethodException {
        var setRestTemplateMethod = BattleServiceImpl.class.getDeclaredMethod("setRestTemplate", RestTemplate.class);
        assertNotNull(setRestTemplateMethod.getAnnotation(Autowired.class));
    }

    @Test
    void setBattleServiceUrl_shouldBeAnnotatedWithValue() throws NoSuchMethodException {
        var setter = BattleServiceImpl.class.getDeclaredMethod("setBattleServiceUrl", String.class);
        var valueAnnotation = setter.getAnnotation(Value.class);
        assertNotNull(valueAnnotation);
        assertEquals("${battle.service.url}", valueAnnotation.value());
    }
}
