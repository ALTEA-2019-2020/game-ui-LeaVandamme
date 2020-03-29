package service;

import com.miage.altea.game_ui.battle.bo.Battle;
import com.miage.altea.game_ui.battle.service.BattleServiceImpl;
import com.miage.altea.game_ui.trainers.service.TrainerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BattleServiceIntegrationTest {
    @Test
    void setBattleUrl_shouldBeAnnotatedWithValue() throws NoSuchMethodException {
        var setter = BattleServiceImpl.class.getDeclaredMethod("setBattleServiceUrl", String.class);
        var valueAnnotation = setter.getAnnotation(Value.class);
        assertNotNull(valueAnnotation);
        assertEquals("${battle.service.url}", valueAnnotation.value());
    }
}
