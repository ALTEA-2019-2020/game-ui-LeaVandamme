package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PokemonTypeController {

    @Autowired
    private PokemonTypeService pokemonTypeService;

    public void setPokemonTypeService(PokemonTypeService pokemonTypeService){
        this.pokemonTypeService = pokemonTypeService;
    }

    @GetMapping("/pokedex")
    public ModelAndView pokedex(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("pokedex");
        mav.addObject("pokemonTypes", pokemonTypeService.listPokemonsTypes());
        return mav;
    }

}
