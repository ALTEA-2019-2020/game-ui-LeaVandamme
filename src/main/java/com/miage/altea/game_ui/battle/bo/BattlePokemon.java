package com.miage.altea.game_ui.battle.bo;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;

public class BattlePokemon {

    private int id;
    private int hp;
    private int maxHp;
    private PokemonType type;
    private int level;
    private int attack;
    private int speed;
    private int defense;
    private boolean ko;

    public BattlePokemon(PokemonType type, int level) {
        this.type = type;
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean isKo() {
        return ko;
    }

    public void setKo(boolean ko) {
        this.ko = ko;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

