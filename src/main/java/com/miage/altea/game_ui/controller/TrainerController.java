package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import com.miage.altea.game_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private PokemonTypeService pokemonService;

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

    @GetMapping("/profile")
    public ModelAndView profile(Principal p){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("trainerDetails");

        List<Trainer> allTrainers = trainerService.listTrainer();
        allTrainers = new ArrayList<Trainer>(allTrainers);
        for(Trainer t : allTrainers){
            if(t.getName().equals(p.getName())){
                allTrainers.remove(t);
            }
        }

        mav.addObject("user", trainerService.getTrainerByName(p.getName()));
        mav.addObject("trainers", allTrainers);

        return mav;
    }
}
