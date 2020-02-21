package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import com.miage.altea.game_ui.pokemonTypes.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    public void setTrainerService(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @GetMapping("/trainers")
    public ModelAndView trainers(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("trainers");
        mav.addObject("trainers", trainerService.listTrainer());
        return mav;
    }

    @GetMapping("/trainers/{name}")
    public ModelAndView trainersDetails(@PathVariable String name){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("trainerDetails");
        mav.addObject("trainer", trainerService.getTrainerByName(name));
        return mav;
    }
}
