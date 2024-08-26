package com.swiggy.MagicalArenaGame.Interfaces;

import com.swiggy.MagicalArenaGame.Exceptions.GameNotStartedException;

public interface MagicalArenaGamable {

    void play();

    void displayResult() throws GameNotStartedException;

    void resetGame();
}