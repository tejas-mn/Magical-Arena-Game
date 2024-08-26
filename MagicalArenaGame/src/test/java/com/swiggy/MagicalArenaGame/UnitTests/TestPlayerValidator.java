package com.swiggy.MagicalArenaGame.UnitTests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runners.Parameterized.Parameters;

import com.swiggy.MagicalArenaGame.Players.Player;
import com.swiggy.MagicalArenaGame.Validations.PlayerValidator;
import com.swiggy.MagicalArenaGame.Exceptions.InvalidPlayerAttributesException;
import com.swiggy.MagicalArenaGame.Exceptions.PlayerDiceMisMatchException;
import com.swiggy.MagicalArenaGame.Factories.DiceFactory;
import com.swiggy.MagicalArenaGame.Factories.DiceFactory.Dice;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableDie;
import com.swiggy.MagicalArenaGame.Interfaces.PlayerValidatable;

@TestInstance(Lifecycle.PER_CLASS)
public class TestPlayerValidator {

	private DiceFactory diceFactory;
	private static PlayableDie attackingDie;
	private static PlayableDie defendingDie;
	private PlayerValidatable playerValidator;

	@BeforeAll
	public void setUpFixture() throws Exception {
		diceFactory = DiceFactory.getInstance();
	}

	@BeforeEach
	public void testSetup() throws Exception {
		playerValidator = new PlayerValidator();
		attackingDie = diceFactory.create(Dice.ATTACKING_DIE);
		defendingDie = diceFactory.create(Dice.DEFENDING_DIE);
	}

	@ParameterizedTest(name = "{3}")
	@MethodSource("playerAttributesProvider")
	public void testInvalidPlayerAttributes(int a, int b, int c, String testName) throws Exception {

		// arrange
		assertThrows(InvalidPlayerAttributesException.class, () -> {
			new Player(1, a, b, c, attackingDie, defendingDie, playerValidator);
		});
	}

	@ParameterizedTest(name = "{2}")
	@MethodSource("misMatchDiceProvider")
	public void testPlayerDiceMisMatches(PlayableDie _attackingDie, PlayableDie _defendingDie, String testName) throws Exception {

		// arrange
		assertThrows(PlayerDiceMisMatchException.class, () -> {
			new Player(1, 10, 20, 30, _attackingDie, _defendingDie, playerValidator);
		});

	}

	@Parameters
	static Collection<Object[]> misMatchDiceProvider() throws Exception {
		Object[][] dice = new Object[][] {
			{ attackingDie, attackingDie, "validatePlayerUsingAttackingDieForDefendingDie" },
			{ defendingDie, defendingDie, "validatePlayerUsingDefendingDieForAttackingDie" },
			{ defendingDie, attackingDie, "validatePlayerSwapingAttackingAndDefendingDice" } 
		};
		return Arrays.asList(dice);
	}

	@Parameters
	static Collection<Arguments> playerAttributesProvider() {
		return Arrays.asList(
			Arguments.of(-10, 10, 10, "validateNegativeHealth"),
			Arguments.of(10, -10, 10, "validateNegativeAttack"),
			Arguments.of(10, 10, -10, "validateNegativeStrength")
		);
	}
}
