package com.swiggy.MagicalArenaGame.Interfaces;

public interface PlayableArena {
	void addPlayer(Playable player) throws Exception;

	void initGame() throws Exception;

	void startNewGame() throws Exception;

	void showGameResults() throws Exception;

	void exit();

	boolean isPlayersEmpty();

	boolean isGameInitialized();
}
