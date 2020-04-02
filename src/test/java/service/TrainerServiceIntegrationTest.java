package service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeServiceImpl;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import com.miage.altea.game_ui.trainers.service.TrainerService;
import com.miage.altea.game_ui.trainers.service.TrainerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import javax.swing.table.TableCellRenderer;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TrainerServiceIntegrationTest {

    @Test
    void listTrainer_shouldReturnAllTrainers() {

        /*String url = "http://localhost:8081";
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        TrainerService trainerService = new TrainerServiceImpl();
        trainerService.setRestTemplate(restTemplate);
        trainerService.setTrainerServiceUrl(url);

        Trainer ash = new Trainer();
        ash.setName("Ash");
        Trainer misty = new Trainer();
        misty.setName("misty");
        Trainer[] trainers = {ash, misty};

        String expectedUrl = "http://localhost:8081/trainers/";
        Mockito.when(restTemplate.getForObject(expectedUrl, Trainer[].class)).thenReturn(trainers);

        List<Trainer> res = trainerService.listTrainer();
        assertNotNull(res);
        assertEquals(2, res.size());

        assertEquals("Misty", res.get(0).getName());*/
    }

    @Test
    void getTrainerByName_shouldReturnATrainer() {

        /*String url = "http://localhost:8081";
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        TrainerService trainerService = new TrainerServiceImpl();
        trainerService.setRestTemplate(restTemplate);
        trainerService.setTrainerServiceUrl(url);

        Trainer ash = new Trainer();
        ash.setName("Ash");
        ash.setPassword("ash_password");

        String expectedUrl = "http://localhost:8081/trainers/";
        Mockito.when(restTemplate.getForObject(expectedUrl+ash.getName(), Trainer.class)).thenReturn(ash);

        Trainer res = trainerService.getTrainerByName(ash.getName());
        assertNotNull(res);
        assertEquals("ash_password", res.getPassword());*/
    }
}
