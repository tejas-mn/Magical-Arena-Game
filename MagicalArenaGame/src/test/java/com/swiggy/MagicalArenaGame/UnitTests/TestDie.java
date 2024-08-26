package com.swiggy.MagicalArenaGame.UnitTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.swiggy.MagicalArenaGame.Dice.SixSidedDie;
import com.swiggy.MagicalArenaGame.Factories.DiceFactory;
import com.swiggy.MagicalArenaGame.Factories.DiceFactory.Dice;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableDie;

@TestInstance(Lifecycle.PER_CLASS)
public class TestDie {

	private DiceFactory diceFactory;

	@BeforeAll
	public void setUpFixture() throws Exception {
		diceFactory = DiceFactory.getInstance();
	}

	@Test
	public void testValidDieIsCreated() throws Exception {
		// arrange
		PlayableDie die = diceFactory.createDieWithRange(1, 3);

		// assert
		assertNotNull("Valid Die should be created", die);
	}

	@Test
	public void testBasicDieRoll() throws Exception {
		// arrange
		PlayableDie basicDie = diceFactory.createDieWithRange(1, 3);

		// act
		int dieNumber = basicDie.roll();

		// assert
		assertTrue("Die should roll a number between 1 and 3", dieNumber >= 1 && dieNumber <= 3);
	}

	@Test
	public void testAttackingDieRoll() throws Exception {
		// arrange
		PlayableDie attackingDie = diceFactory.create(Dice.ATTACKING_DIE);

		// act
		int dieNumber = attackingDie.roll();

		// assert
		assertTrue("Attacking Die should roll a number between 1 and 6", dieNumber >= 1 && dieNumber <= 6);
	}

	@Test
	public void testDefendingDieRoll() throws Exception {
		// arrange
		PlayableDie defendingDie = diceFactory.create(Dice.DEFENDING_DIE);

		// act
		int dieNumber = defendingDie.roll();

		// assert
		assertTrue("Defending Die should roll a number between 1 and 6", dieNumber >= 1 && dieNumber <= 6);
	}

	@Test
	public void testSixSidedDieRoll() throws Exception {
		// arrange
		PlayableDie sixSidedDie = diceFactory.create(Dice.SIX_SIDED_DIE);

		// act
		int dieNumber = sixSidedDie.roll();

		// assert
		assertTrue("Six Sided Die should roll a number between 1 and 6", dieNumber >= 1 && dieNumber <= 6);
	}

	@Test
	public void testAttackingDieisSixSided() throws Exception {
		// arrange
		PlayableDie attackingDie = diceFactory.create(Dice.ATTACKING_DIE);

		// assert
		assertTrue("Attacking Die should be a Six Sided Die", attackingDie instanceof SixSidedDie);
	}

	@Test
	public void testDefendingDieisSixSided() throws Exception {
		// arrange
		PlayableDie defendingDie = diceFactory.create(Dice.DEFENDING_DIE);

		// assert
		assertTrue("Defending Die should be a Six Sided Die", defendingDie instanceof SixSidedDie);
	}
}
