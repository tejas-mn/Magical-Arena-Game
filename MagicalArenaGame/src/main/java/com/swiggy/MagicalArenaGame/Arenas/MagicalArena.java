package com.swiggy.MagicalArenaGame.Arenas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.swiggy.MagicalArenaGame.Exceptions.GameNotInitializedException;
import com.swiggy.MagicalArenaGame.Exceptions.GameNotStartedException;
import com.swiggy.MagicalArenaGame.Exceptions.InsufficientPlayersException;
import com.swiggy.MagicalArenaGame.Exceptions.PlayerLimitReachedException;
import com.swiggy.MagicalArenaGame.Games.MagicalArenaGame;
import com.swiggy.MagicalArenaGame.Interfaces.MagicalArenaGamable;
import com.swiggy.MagicalArenaGame.Interfaces.Playable;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableArena;

public class MagicalArena implements PlayableArena {

	private Set<Playable> players;
	private static final int MAX_PLAYERS = 2;
	private MagicalArenaGamable game;

	public MagicalArena() {
		players = new HashSet<>(MAX_PLAYERS);
	}

	@Override
	public void initGame() throws Exception {
		if (players.size() < MAX_PLAYERS) {
			throw new InsufficientPlayersException(
					"Insufficient Players! Please add players to the Arena before initializing the game!");
		}
		List<Playable> playerList = new ArrayList<>(players);
		game = new MagicalArenaGame(playerList.get(0), playerList.get(1));
	}

	@Override
	public void startNewGame() throws GameNotInitializedException {
		checkGameInitialized();
		game.resetGame();
		game.play();
	}

	@Override
	public void showGameResults() throws GameNotStartedException, GameNotInitializedException {
		checkGameInitialized();
		game.displayResult();
	}

	@Override
	public void addPlayer(Playable player) throws PlayerLimitReachedException {
		if (players.size() >= MAX_PLAYERS) {
			throw new PlayerLimitReachedException("Players Full! Remove some players or start a new Game!");
		}
		players.add(player);
	}

	@Override
	public void exit() {
		players.clear();
		game = null;
	}

	private void checkGameInitialized() throws GameNotInitializedException {
		if (!isGameInitialized()) {
			throw new GameNotInitializedException("Please initialize the game!");
		}
	}

	@Override
	public boolean isPlayersEmpty() {
		return players.size() == 0;
	}

	@Override
	public boolean isGameInitialized() {
		return game != null;
	}
}
