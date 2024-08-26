package com.swiggy.MagicalArenaGame.UnitTests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.swiggy.MagicalArenaGame.Factories.DiceFactory;
import com.swiggy.MagicalArenaGame.Factories.DiceFactory.Dice;
import com.swiggy.MagicalArenaGame.Interfaces.Playable;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableDie;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableSwappable;
import com.swiggy.MagicalArenaGame.Interfaces.PlayerValidatable;
import com.swiggy.MagicalArenaGame.Players.Player;
import com.swiggy.MagicalArenaGame.Utility.PlayableSwapper;
import com.swiggy.MagicalArenaGame.Validations.PlayerValidator;

@TestInstance(Lifecycle.PER_CLASS)
public class TestUtility {

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
	public void testPlayableSwapperSwapsPlayers() throws Exception {

		// arrange
		Playable playerA = new Player(1, 50, 5, 10, attackingDie, defendingDie, playerValidator);
		Playable playerB = new Player(2, 80, 10, 5, attackingDie, defendingDie, playerValidator);

		PlayableSwappable playerSwapper = new PlayableSwapper(playerA, playerB);

		// act
		playerSwapper.swapPlayables();

		// assert
		assertTrue("First Player should be Second Player after swap", playerSwapper.getFirstPlayable() == playerB);
		assertTrue("Second Player should be First Player after swap", playerSwapper.getSecondPlayable() == playerA);
	}
}
