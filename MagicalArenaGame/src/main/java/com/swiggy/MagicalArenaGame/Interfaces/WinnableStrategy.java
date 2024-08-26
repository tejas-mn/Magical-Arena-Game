package com.swiggy.MagicalArenaGame.Interfaces;

import com.swiggy.MagicalArenaGame.Exceptions.GameNotStartedException;

public interface WinnableStrategy {
	Playable determineWinner(Playable player1, Playable player2) throws GameNotStartedException;
}