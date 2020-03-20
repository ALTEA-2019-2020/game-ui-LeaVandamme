package com.miage.altea.game_ui.pokemonTypes.bo;


import javax.persistence.*;

@Embeddable
public class Pokemon {

    private int pokemonTypeId;

    private int level;

    @Transient
    private PokemonType pt;

    public Pokemon() {
    }

    public Pokemon(int pokemonTypeId, int level) {
        this.pokemonTypeId = pokemonTypeId;
        this.level = level;
    }

    public int getPokemonTypeId() {
        return pokemonTypeId;
    }

    public void setPokemonTypeId(int pokemonTypeId) {
        this.pokemonTypeId = pokemonTypeId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPt(PokemonType pt) {
        this.pt = pt;
    }

    public PokemonType getPt() {
        return pt;
    }
}
