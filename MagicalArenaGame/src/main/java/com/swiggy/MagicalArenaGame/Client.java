package com.swiggy.MagicalArenaGame;

import com.swiggy.MagicalArenaGame.Arenas.MagicalArena;
import com.swiggy.MagicalArenaGame.Factories.DiceFactory;
import com.swiggy.MagicalArenaGame.Interfaces.Playable;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableArena;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableDie;
import com.swiggy.MagicalArenaGame.Players.Player;
import com.swiggy.MagicalArenaGame.Validations.PlayerValidator;
import com.swiggy.MagicalArenaGame.Interfaces.PlayerValidatable;

public class Client {

	private static final int PLAYER_A_ID = 1;
	private static final int PLAYER_A_HEALTH = 50;
	private static final int PLAYER_A_ATTACK = 5;
	private static final int PLAYER_A_DEFENSE = 10;

	private static final int PLAYER_B_ID = 2;
	private static final int PLAYER_B_HEALTH = 100;
	private static final int PLAYER_B_ATTACK = 10;
	private static final int PLAYER_B_DEFENSE = 5;

	public static void main(String[] args) {
		try {
			DiceFactory diceFactory = DiceFactory.getInstance();

			PlayableDie attackingDie = diceFactory.create(DiceFactory.Dice.ATTACKING_DIE);
			PlayableDie defendingDie = diceFactory.create(DiceFactory.Dice.DEFENDING_DIE);

			PlayerValidatable playerValidator = new PlayerValidator();

			Playable playerA = new Player(PLAYER_A_ID, PLAYER_A_HEALTH, PLAYER_A_ATTACK, PLAYER_A_DEFENSE, attackingDie, defendingDie, playerValidator);
			Playable playerB = new Player(PLAYER_B_ID, PLAYER_B_HEALTH, PLAYER_B_ATTACK, PLAYER_B_DEFENSE, attackingDie, defendingDie, playerValidator);

			PlayableArena magicalArena = new MagicalArena();

			magicalArena.addPlayer(playerA);
			magicalArena.addPlayer(playerB);

			playGame(magicalArena, 2);

		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void playGame(PlayableArena magicalArena, int turns) throws Exception {
		magicalArena.initGame();

		for (int i = 0; i < turns; i++) {
			magicalArena.startNewGame();
			magicalArena.showGameResults();
		}

		magicalArena.exit();
	}
}
