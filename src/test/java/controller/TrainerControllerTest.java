package controller;

import com.miage.altea.game_ui.controller.TrainerController;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import com.miage.altea.game_ui.trainers.service.TrainerService;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerControllerTest {
    @Test
    void controllerShouldBeAnnotated() {
        assertNotNull(TrainerController.class.getAnnotation(Controller.class));
    }

    @Test
    void pokemons_shouldReturnAModelAndView() {
        var trainerService = mock(TrainerService.class);

        when(trainerService.listTrainer()).thenReturn(List.of(new Trainer(), new Trainer()));

        var trainerController = new TrainerController();
        trainerController.setTrainerService(trainerService);
        var modelAndView = trainerController.trainers();

        assertEquals("trainers", modelAndView.getViewName());
        var trainers = (List<Trainer>) modelAndView.getModel().get("trainers");
        assertEquals(2, trainers.size());
        verify(trainerService).listTrainer();
    }

    @Test
    void pokemons_shouldBeAnnotated() throws NoSuchMethodException {
        var pokemonsMethod = TrainerController.class.getDeclaredMethod("trainers");
        var getMapping = pokemonsMethod.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/trainers"}, getMapping.value());
    }
}
