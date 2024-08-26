package com.swiggy.MagicalArenaGame.Interfaces;

import com.swiggy.MagicalArenaGame.Exceptions.GameNotStartedException;

public interface GamableStrategy {
	void fight(Playable playerA, Playable playerB);

	void determineWinner(Playable playerA, Playable playerB) throws GameNotStartedException;
	
}
