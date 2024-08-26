package com.swiggy.MagicalArenaGame.Strategies;

import com.swiggy.MagicalArenaGame.Exceptions.GameNotStartedException;
import com.swiggy.MagicalArenaGame.Interfaces.Playable;
import com.swiggy.MagicalArenaGame.Interfaces.WinnableStrategy;

public class WinningStrategy implements WinnableStrategy {

    @Override
    public Playable determineWinner(Playable player1, Playable player2) throws GameNotStartedException {
        if (player1.isAlive() && player2.isAlive()) {
            throw new GameNotStartedException("Both players are alive. The game has not concluded.");
        }

        return player1.isAlive() ? player1 : player2;
    }
}
