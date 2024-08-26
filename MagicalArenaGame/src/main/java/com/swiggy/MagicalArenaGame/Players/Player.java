package com.swiggy.MagicalArenaGame.Players;

import com.swiggy.MagicalArenaGame.Interfaces.Playable;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableDie;
import com.swiggy.MagicalArenaGame.Interfaces.PlayerValidatable;

public class Player implements Playable {

    private int health;
    private int strength;
    private int attack;

    private final int originalHealth;
    private final int originalStrength;
    private final int originalAttack;

    private final int id;
    private final PlayableDie attackingDie;
    private final PlayableDie defendingDie;
    private final PlayerValidatable playerValidator;

    public Player(int id, int health, int strength, int attack, PlayableDie attackingDie, PlayableDie defendingDie, PlayerValidatable playerValidator) throws Exception {
        this.id = id;
        this.health = health;
        this.strength = strength;
        this.attack = attack;
        this.originalAttack = attack;
        this.originalHealth = health;
        this.originalStrength = strength;
        this.attackingDie = attackingDie;
        this.defendingDie = defendingDie;
        this.playerValidator = playerValidator;
        this.playerValidator.Validate(this);
        this.playerValidator.ValidatePlayerDice(this.attackingDie, this.defendingDie);
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getAttack() {
        return this.attack;
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int attack() {
        return this.attack * this.attackingDie.roll();
    }

    @Override
    public int defend() {
        return this.strength * this.defendingDie.roll();
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public void decreaseHealth(int damagedHealth) {
        this.health = Math.max(0, this.health - damagedHealth);
    }

    @Override
    public void resetPlayerAttributes() {
        this.health = this.originalHealth;
        this.attack = this.originalAttack;
        this.strength = this.originalStrength;
    }
}