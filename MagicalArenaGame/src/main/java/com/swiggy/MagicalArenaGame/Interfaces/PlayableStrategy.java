package com.swiggy.MagicalArenaGame.Interfaces;

public interface PlayableStrategy {
	Playable chooseStartingPlayer(Playable player1, Playable player2);

	void play(Playable attacker, Playable defender);
}
