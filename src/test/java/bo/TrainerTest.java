package bo;

import com.miage.altea.game_ui.trainers.bo.Trainer;
import org.junit.jupiter.api.Test;

import javax.persistence.Entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TrainerTest {

    @Test
    void pokemon_shouldBeAnEntity(){
        assertNotNull(Trainer.class.getAnnotation(Entity.class));
    }
}
