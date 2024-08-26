package com.swiggy.MagicalArenaGame.Games;

import com.swiggy.MagicalArenaGame.Exceptions.GameNotStartedException;
import com.swiggy.MagicalArenaGame.Exceptions.GameValidationException;
import com.swiggy.MagicalArenaGame.Interfaces.GamableStrategy;
import com.swiggy.MagicalArenaGame.Interfaces.MagicalArenaGamable;
import com.swiggy.MagicalArenaGame.Interfaces.Playable;
import com.swiggy.MagicalArenaGame.Strategies.GamingStrategy;
import com.swiggy.MagicalArenaGame.Validations.MagicalArenaGameValidator;

public class MagicalArenaGame implements MagicalArenaGamable {
    
    private GamableStrategy gameStrategy; 
    private MagicalArenaGameValidator gameValidator;
    private Playable playerA;
    private Playable playerB;
    public static final int NUM_PLAYERS = 2;
    
    public MagicalArenaGame(Playable playerA, Playable playerB) throws GameValidationException {
        this.playerA = playerA;
        this.playerB = playerB;
        gameValidator = new MagicalArenaGameValidator();
        gameValidator.validatePlayers(playerA, playerB);
        gameStrategy = new GamingStrategy();
    }
    
    public void play() {
        gameStrategy.fight(playerA, playerB);
    }
 
    public void displayResult() throws GameNotStartedException {
        gameStrategy.determineWinner(playerA, playerB);
    }
    
    public void resetGame() {
        restartPlayers();
    }
    
    private void restartPlayers() {
        playerA.resetPlayerAttributes();
        playerB.resetPlayerAttributes();
    }
}
