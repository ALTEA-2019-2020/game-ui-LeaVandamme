package bo;

import com.miage.altea.game_ui.pokemonTypes.bo.Pokemon;
import org.junit.jupiter.api.Test;

import javax.persistence.Embeddable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PokemonTest {
    @Test
    void pokemon_shouldBeAnEmbeddable(){
        assertNotNull(Pokemon.class.getAnnotation(Embeddable.class));
    }
}
