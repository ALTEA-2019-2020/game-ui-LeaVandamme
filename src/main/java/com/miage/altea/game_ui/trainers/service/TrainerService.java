package com.miage.altea.game_ui.trainers.service;

import com.miage.altea.game_ui.trainers.bo.Trainer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface TrainerService {
    List<Trainer> listTrainer();
    Trainer getTrainerByName(String name);
    void setRestTemplate(RestTemplate restTemplate);
    void setTrainerServiceUrl(String trainerServiceUrl);

}
