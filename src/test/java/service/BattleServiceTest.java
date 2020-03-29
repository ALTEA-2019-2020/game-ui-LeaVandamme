package service;

import com.miage.altea.game_ui.battle.bo.Battle;
import com.miage.altea.game_ui.battle.bo.BattleTrainer;
import com.miage.altea.game_ui.battle.service.BattleServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BattleServiceTest {

    @Autowired
    BattleServiceImpl battleServiceImpl;

    @Test
    void getBattles_shouldCallTheRemoteService() {
        var url = "http://localhost:8082";

        var restTemplate = mock(RestTemplate.class);
        //var battleServiceImpl = new BattleServiceImpl();
        battleServiceImpl.setRestTemplate(restTemplate);
        battleServiceImpl.setBattleServiceUrl(url);

        var battleTrainer = new BattleTrainer("ash", true);
        var battle = new Battle();
        battle.setTrainer(battleTrainer);

        var expectedUrl = "http://localhost:8082/battles";
        Mockito.when(battleServiceImpl.getBattles()).thenReturn(Arrays.asList(new Battle[]{battle}));

        var battles = battleServiceImpl.getBattles();

        assertNotNull(battles);
        assertEquals(1, battles.size());

        verify(restTemplate).getForObject(expectedUrl, Battle[].class);
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
