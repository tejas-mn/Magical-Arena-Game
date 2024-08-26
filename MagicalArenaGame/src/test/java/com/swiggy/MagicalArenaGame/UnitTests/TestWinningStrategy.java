package com.swiggy.MagicalArenaGame.UnitTests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.swiggy.MagicalArenaGame.Exceptions.GameNotStartedException;
import com.swiggy.MagicalArenaGame.Factories.DiceFactory;
import com.swiggy.MagicalArenaGame.Factories.DiceFactory.Dice;
import com.swiggy.MagicalArenaGame.Interfaces.Playable;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableDie;
import com.swiggy.MagicalArenaGame.Interfaces.PlayerValidatable;
import com.swiggy.MagicalArenaGame.Interfaces.WinnableStrategy;
import com.swiggy.MagicalArenaGame.Players.Player;
import com.swiggy.MagicalArenaGame.Strategies.WinningStrategy;
import com.swiggy.MagicalArenaGame.Validations.PlayerValidator;

@TestInstance(Lifecycle.PER_CLASS)
public class TestWinningStrategy {

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
	public void testdetermineWinnerThrowsExceptionWhenBothPlayersAreAlive() throws Exception {

		// arrange
		Playable playerA = new Player(1, 20, 5, 50, attackingDie, defendingDie, playerValidator);
		Playable playerB = new Player(2, 10, 1, 5, attackingDie, defendingDie, playerValidator);

		WinnableStrategy winningStrategy = new WinningStrategy();

		// assert
		assertThrows(GameNotStartedException.class, () -> {
			winningStrategy.determineWinner(playerA, playerB);
		});
	}

	@Test
	public void testPlayerWhoIsAliveWins() throws Exception {

		// arrange
		Playable playerA = new Player(1, 20, 5, 50, attackingDie, defendingDie, playerValidator);
		Playable playerB = new Player(2, 10, 1, 5, attackingDie, defendingDie, playerValidator);

		// make playerB dead
		playerB.decreaseHealth(20);
		WinnableStrategy winningStrategy = new WinningStrategy();

		// act
		Playable winner = winningStrategy.determineWinner(playerA, playerB);

		// assert
		assertTrue("Player A should be Alive and is the Winner", winner == playerA);
		assertTrue("Player B should be Dead", !playerB.isAlive());
	}
}
