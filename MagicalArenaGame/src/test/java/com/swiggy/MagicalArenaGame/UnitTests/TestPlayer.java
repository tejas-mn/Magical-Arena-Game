package com.swiggy.MagicalArenaGame.UnitTests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.swiggy.MagicalArenaGame.Factories.DiceFactory;
import com.swiggy.MagicalArenaGame.Factories.DiceFactory.Dice;
import com.swiggy.MagicalArenaGame.Interfaces.Playable;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableDie;
import com.swiggy.MagicalArenaGame.Interfaces.PlayerValidatable;
import com.swiggy.MagicalArenaGame.Players.Player;
import com.swiggy.MagicalArenaGame.Validations.PlayerValidator;

@TestInstance(Lifecycle.PER_CLASS)
public class TestPlayer {

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
	public void testValidPlayerIsCreated() throws Exception {

		// arrange
		Playable player = new Player(1, 10, 10, 10, attackingDie, defendingDie, playerValidator);

		// assert
		assertNotNull("Valid Player should be Created", player);
	}

	@Test
	public void testPlayerAttack() throws Exception {

		// arrange
		Playable player = new Player(1, 10, 10, 10, attackingDie, defendingDie, playerValidator);

		// act
		int attackDamage = player.attack();

		// assert
		assertTrue("Attack damage should be positive", attackDamage > 0);
	}

	@Test
	public void testPlayerDefend() throws Exception {

		// arrange
		Playable player = new Player(1, 10, 10, 10, attackingDie, defendingDie, playerValidator);

		// act
		int defendStrength = player.defend();

		// assert
		assertTrue("Defend strength should be positive", defendStrength > 0);
	}

	@Test
	public void testPlayerHealthDecreasesWithDamage() throws Exception {

		// arrange
		Playable player = new Player(1, 50, 10, 10, attackingDie, defendingDie, playerValidator);

		// act
		player.decreaseHealth(20);

		// assert
		assertTrue("Player health should be decreased to 30", player.getHealth() == 30);
	}

	@Test
	public void testPlayerIsAliveAfterPartialDamage() throws Exception {

		// arrange
		Playable player = new Player(1, 50, 10, 10, attackingDie, defendingDie, playerValidator);

		// act
		player.decreaseHealth(20);

		// assert
		assertTrue("Player should be Alive", player.isAlive());
	}

	@Test
	public void testPlayerIsDeadAfterFullDamage() throws Exception {

		// arrange
		Playable player = new Player(1, 50, 10, 10, attackingDie, defendingDie, playerValidator);

		// act
		player.decreaseHealth(60);

		// assert
		assertTrue("Player should not be Alive", !player.isAlive());
	}

	@Test
	public void testPlayerHealthResets() throws Exception {

		// arrange
		Playable player = new Player(1, 50, 10, 10, attackingDie, defendingDie, playerValidator);

		// act
		player.decreaseHealth(50);
		player.resetPlayerAttributes();

		// assert
		assertTrue("Player health should be reset to 50", player.getHealth() == 50);
	}
}