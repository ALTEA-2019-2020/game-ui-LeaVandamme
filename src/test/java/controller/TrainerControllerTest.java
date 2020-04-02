package controller;

import com.miage.altea.game_ui.controller.TrainerController;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import com.miage.altea.game_ui.trainers.service.TrainerService;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerControllerTest {
    @Test
    void controllerShouldBeAnnotated() {
        assertNotNull(TrainerController.class.getAnnotation(Controller.class));
    }

    @Test
    void trainers_shouldReturnAModelAndView() {
        var trainerService = mock(TrainerService.class);
        Trainer trainerAsh = new Trainer();
        trainerAsh.setName("Ash");
        Trainer trainerMisty = new Trainer();
        trainerMisty.setName("Misty");
        when(trainerService.listTrainer()).thenReturn(List.of(trainerAsh, trainerMisty));

        var trainerController = new TrainerController();
        trainerController.setTrainerService(trainerService);
        var modelAndView = trainerController.trainers(() -> "Ash");

        assertEquals("trainers", modelAndView.getViewName());
        var trainers = (List<Trainer>) modelAndView.getModel().get("trainers");
        //Doit retourner 1 car on n'affiche que les trainers autres que celui connecté
        assertEquals(1, trainers.size());
        verify(trainerService).listTrainer();
    }

    @Test
    void trainers_shouldBeAnnotated() throws NoSuchMethodException {
        var trainersMethod = TrainerController.class.getDeclaredMethod("trainers", Principal.class);
        var getMapping = trainersMethod.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/trainers"}, getMapping.value());
    }

    @Test
    void trainersDetails_shouldBeAnnotated() throws NoSuchMethodException {
        var trainersMethod = TrainerController.class.getDeclaredMethod("trainersDetails", String.class);
        var getMapping = trainersMethod.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/trainers/{name}"}, getMapping.value());
    }

    @Test
    void profile_shouldBeAnnotated() throws NoSuchMethodException {
        var trainersMethod = TrainerController.class.getDeclaredMethod("profile", Principal.class);
        var getMapping = trainersMethod.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/profile"}, getMapping.value());
    }
}
