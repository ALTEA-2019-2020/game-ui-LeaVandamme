package service;

import com.miage.altea.game_ui.pokemonTypes.service.TrainerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import static org.junit.jupiter.api.Assertions.*;


public class TrainerIntegrationTest {
    @Test
    void setTrainerUrl_shouldBeAnnotatedWithValue() throws NoSuchMethodException {
        var setter = TrainerServiceImpl.class.getDeclaredMethod("setTrainerServiceUrl", String.class);
        var valueAnnotation = setter.getAnnotation(Value.class);
        assertNotNull(valueAnnotation);
        assertEquals("${trainer.service.url}", valueAnnotation.value());
    }
}
