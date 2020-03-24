package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.config.Interceptor;
import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private RestTemplate restTemplate;
    private String pokemonServiceUrl;

    public List<PokemonType> listPokemonsTypes() {

        return Arrays.asList(restTemplate.getForObject(pokemonServiceUrl+"pokemon-types/", PokemonType[].class));
    }

    @Override
    public PokemonType getPokemonType(int id) {
        var pokemon = restTemplate.getForObject(pokemonServiceUrl+"/pokemon-types/{id}",PokemonType.class,id);
        return pokemon;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}
