package com.swiggy.MagicalArenaGame.Validations;

import com.swiggy.MagicalArenaGame.Exceptions.GameValidationException;
import com.swiggy.MagicalArenaGame.Interfaces.Playable;

public class MagicalArenaGameValidator {
	public void validatePlayers(Playable player1, Playable player2) throws GameValidationException {
		if(player1 == player2) throw new GameValidationException("Choose different players!");
	}
}
