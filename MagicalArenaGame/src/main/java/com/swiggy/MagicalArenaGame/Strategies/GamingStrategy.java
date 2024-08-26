package com.swiggy.MagicalArenaGame.Strategies;

import com.swiggy.MagicalArenaGame.Exceptions.GameNotStartedException;
import com.swiggy.MagicalArenaGame.Interfaces.GamableStrategy;
import com.swiggy.MagicalArenaGame.Interfaces.Playable;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableStrategy;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableSwappable;
import com.swiggy.MagicalArenaGame.Interfaces.WinnableStrategy;
import com.swiggy.MagicalArenaGame.Utility.PlayableSwapper;

public class GamingStrategy implements GamableStrategy {

    private final WinnableStrategy winningStrategy;
    private final PlayableStrategy playingStrategy;

    public GamingStrategy() {
        this.winningStrategy = new WinningStrategy();
        this.playingStrategy = new PlayingStrategy();
    }

    @Override
    public void fight(Playable playerA, Playable playerB) {
        System.out.println("\n\t   <<< Game Started >>>");

        Playable attacker = playingStrategy.chooseStartingPlayer(playerA, playerB);
        Playable defender = attacker == playerA ? playerB : playerA;

        PlayableSwappable playerSwapper = new PlayableSwapper(attacker, defender);

        System.out.println("\n--------- Player " + attacker.getId() + " starts the game ---------\n");

        while (attacker.isAlive() && defender.isAlive()) {
            displayHealthStatus(attacker, defender);

            playingStrategy.play(attacker, defender);

            playerSwapper.swapPlayables();
            attacker = playerSwapper.getFirstPlayable();
            defender = playerSwapper.getSecondPlayable();

            System.out.println("--------------------------------------------");
        }

        displayHealthStatus(attacker, defender);

        System.out.println("\n\t   <<< Game Ended >>>\n");
    }

    @Override
    public void determineWinner(Playable playerA, Playable playerB) throws GameNotStartedException {
        try {
            Playable winner = winningStrategy.determineWinner(playerA, playerB);
            System.out.println("------------- Player " + winner.getId() + " Wins ----------------");
        } catch (GameNotStartedException e) {
            System.out.println("------------- Please start the Game !! ----------------");
            throw e;
        }
    }

    private void displayHealthStatus(Playable player1, Playable player2) {
        System.out.println("Player " + player1.getId() + " Health: " + player1.getHealth() + " | " + "Player " + player2.getId() + " Health: " + player2.getHealth());
    }
}
