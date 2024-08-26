package com.swiggy.MagicalArenaGame.Strategies;

import com.swiggy.MagicalArenaGame.Interfaces.Playable;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableStrategy;

public class PlayingStrategy implements PlayableStrategy {

    @Override
    public Playable chooseStartingPlayer(Playable player1, Playable player2) {
        return player1.getHealth() < player2.getHealth() ? player1 : player2;
    }

    @Override
    public void play(Playable attacker, Playable defender) {
        int attack = attacker.attack();
        int defense = defender.defend();

        System.out.println("Player " + attacker.getId() + " Attack: " + attack + " | " + "Player " + defender.getId() + " Defense: " + defense);

        if (attack > defense) {
            defender.decreaseHealth(attack - defense);
        }
    }
}
