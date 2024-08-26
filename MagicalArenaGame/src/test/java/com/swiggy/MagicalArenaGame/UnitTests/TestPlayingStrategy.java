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
import com.swiggy.MagicalArenaGame.Interfaces.PlayableStrategy;
import com.swiggy.MagicalArenaGame.Interfaces.PlayerValidatable;
import com.swiggy.MagicalArenaGame.Players.Player;
import com.swiggy.MagicalArenaGame.Strategies.PlayingStrategy;
import com.swiggy.MagicalArenaGame.Validations.PlayerValidator;

@TestInstance(Lifecycle.PER_CLASS)
public class TestPlayingStrategy {

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
	public void testPlayerWithLesserHealthIsChoosenFirst() throws Exception {

		// arrange
		Playable playerA = new Player(1, 50, 5, 10, attackingDie, defendingDie, playerValidator);
		Playable playerB = new Player(2, 20, 10, 5, attackingDie, defendingDie, playerValidator);

		PlayableStrategy playingStrategy = new PlayingStrategy();

		// act
		Playable firstPlayer = playingStrategy.chooseStartingPlayer(playerA, playerB);

		// assert
		assertTrue("Player 2 with lesser health should be choosen first", firstPlayer == playerB);
	}

	@Test
	public void testDefenderHealthReducesWhenAttackIsGreaterThanDefence() throws Exception {

		// arrange
		Playable attacker = new Player(1, 20, 5, 50, attackingDie, defendingDie, playerValidator);
		Playable defender = new Player(2, 50, 1, 5, attackingDie, defendingDie, playerValidator);

		PlayableStrategy playingStrategy = new PlayingStrategy();

		// act
		playingStrategy.play(attacker, defender);

		// assert
		assertTrue("Defender health should be reduced", defender.getHealth() < 50);
		assertTrue("Attacker health should remain same", attacker.getHealth() == 20);
	}

	@Test
	public void testPlayersHealthRemainsSameWhenAttackIsLessThanOrEqualToDefence() throws Exception {

		// arrange
		Playable attacker = new Player(1, 20, 5, 1, attackingDie, defendingDie, playerValidator);
		Playable defender = new Player(2, 50, 50, 5, attackingDie, defendingDie, playerValidator);

		PlayableStrategy playingStrategy = new PlayingStrategy();

		// act
		playingStrategy.play(attacker, defender);

		// assert
		assertTrue("Defender health should remain same", defender.getHealth() == 50);
		assertTrue("Attacker health should remain same", attacker.getHealth() == 20);
	}
}
