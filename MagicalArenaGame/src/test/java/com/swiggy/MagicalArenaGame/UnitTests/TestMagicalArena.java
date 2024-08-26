package com.swiggy.MagicalArenaGame.UnitTests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.swiggy.MagicalArenaGame.Arenas.MagicalArena;
import com.swiggy.MagicalArenaGame.Exceptions.GameNotInitializedException;
import com.swiggy.MagicalArenaGame.Exceptions.GameNotStartedException;
import com.swiggy.MagicalArenaGame.Exceptions.PlayerLimitReachedException;
import com.swiggy.MagicalArenaGame.Factories.DiceFactory;
import com.swiggy.MagicalArenaGame.Factories.DiceFactory.Dice;
import com.swiggy.MagicalArenaGame.Exceptions.InsufficientPlayersException;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableDie;
import com.swiggy.MagicalArenaGame.Interfaces.PlayerValidatable;
import com.swiggy.MagicalArenaGame.Interfaces.Playable;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableArena;
import com.swiggy.MagicalArenaGame.Players.Player;
import com.swiggy.MagicalArenaGame.Validations.PlayerValidator;

@TestInstance(Lifecycle.PER_CLASS)
public class TestMagicalArena {

	private DiceFactory diceFactory;
	private PlayerValidatable playerValidator;
	private PlayableDie attackingDie;
	private PlayableDie defendingDie;

	@BeforeAll
	public void setUpFixture() throws Exception {
		diceFactory = DiceFactory.getInstance();
	}

	@BeforeEach
	public void testSetup() {
		playerValidator = new PlayerValidator();
		attackingDie = diceFactory.create(Dice.ATTACKING_DIE);
		defendingDie = diceFactory.create(Dice.DEFENDING_DIE);
	}

	@Test
	public void testMagicalArenaThrowsInsufficientPlayersException() throws Exception {

		System.out.println(playerValidator == null);
		// arrange
		Playable playerA = new Player(1, 50, 5, 10, attackingDie, defendingDie, playerValidator);
		PlayableArena magicalArena = new MagicalArena();

		// act
		magicalArena.addPlayer(playerA);

		// assert
		assertThrows(InsufficientPlayersException.class, () -> {
			magicalArena.initGame();
		});
	}

	@Test
	public void testMagicalArenaThrowsPlayerLimitReachedException() throws Exception {

		// arrange
		Playable playerA = new Player(1, 50, 5, 10, attackingDie, defendingDie, playerValidator);
		Playable playerB = new Player(2, 80, 10, 5, attackingDie, defendingDie, playerValidator);
		Playable playerC = new Player(2, 80, 10, 5, attackingDie, defendingDie, playerValidator);
		PlayableArena magicalArena = new MagicalArena();

		// act
		magicalArena.addPlayer(playerA);
		magicalArena.addPlayer(playerB);

		// assert
		assertThrows(PlayerLimitReachedException.class, () -> {
			magicalArena.addPlayer(playerC);
		});
	}

	@Test
	public void testMagicalArenaThrowsGameNotInitializedExceptionIfNotInitialized() throws Exception {

		// arrange
		Playable playerA = new Player(1, 50, 5, 10, attackingDie, defendingDie, playerValidator);
		Playable playerB = new Player(2, 80, 10, 5, attackingDie, defendingDie, playerValidator);

		PlayableArena magicalArena = new MagicalArena();

		magicalArena.addPlayer(playerA);
		magicalArena.addPlayer(playerB);

		// assert
		assertThrows(GameNotInitializedException.class, () -> {
			magicalArena.showGameResults();
		});
	}

	@Test
	public void testShowGameResultsThrowsExceptionWithoutStartingNewGame() throws Exception {

		// arrange
		Playable playerA = new Player(1, 50, 5, 10, attackingDie, defendingDie, playerValidator);
		Playable playerB = new Player(2, 80, 10, 5, attackingDie, defendingDie, playerValidator);

		PlayableArena magicalArena = new MagicalArena();

		magicalArena.addPlayer(playerA);
		magicalArena.addPlayer(playerB);
		magicalArena.initGame();

		// assert
		assertThrows(GameNotStartedException.class, () -> {
			magicalArena.showGameResults();
		});
	}

	@Test
	public void testShowGameResultsWorksAfterStartingNewGame() throws Exception {

		// arrange
		Playable playerA = new Player(1, 50, 5, 10, attackingDie, defendingDie, playerValidator);
		Playable playerB = new Player(2, 80, 10, 5, attackingDie, defendingDie, playerValidator);

		PlayableArena magicalArena = new MagicalArena();

		magicalArena.addPlayer(playerA);
		magicalArena.addPlayer(playerB);
		magicalArena.initGame();

		// act
		magicalArena.startNewGame();
		magicalArena.showGameResults();

		// assert
		assertTrue("Either of the players should be alive and Game Results Should Be Displayed In Console",
				playerA.isAlive() ^ playerB.isAlive());
	}

	@Test
	public void testMultipleStartNewGame() throws Exception {

		// arrange
		Playable playerA = new Player(1, 50, 5, 10, attackingDie, defendingDie, playerValidator);
		Playable playerB = new Player(2, 80, 10, 5, attackingDie, defendingDie, playerValidator);

		PlayableArena magicalArena = new MagicalArena();

		magicalArena.addPlayer(playerA);
		magicalArena.addPlayer(playerB);
		magicalArena.initGame();

		// act
		magicalArena.startNewGame();
		magicalArena.showGameResults();

		magicalArena.startNewGame();
		magicalArena.showGameResults();

		// assert
		assertTrue("Either of the players should be alive after they play multiple times",
				playerA.isAlive() ^ playerB.isAlive());
	}

	@Test
	public void testMagicalArenaExit() throws Exception {

		// arrange
		Playable playerA = new Player(1, 50, 5, 10, attackingDie, defendingDie, playerValidator);
		Playable playerB = new Player(2, 80, 10, 5, attackingDie, defendingDie, playerValidator);

		PlayableArena magicalArena = new MagicalArena();

		magicalArena.addPlayer(playerA);
		magicalArena.addPlayer(playerB);
		magicalArena.initGame();

		magicalArena.startNewGame();
		magicalArena.showGameResults();

		// act
		magicalArena.exit();

		// assert
		assertTrue("On Exit Players should be removed and game must be null!",
				magicalArena.isPlayersEmpty() && !magicalArena.isGameInitialized());
	}

}
