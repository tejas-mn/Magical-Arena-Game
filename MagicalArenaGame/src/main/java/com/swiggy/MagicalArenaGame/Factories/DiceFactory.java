package com.swiggy.MagicalArenaGame.Factories;

import java.util.HashMap;
import java.util.Map;

import com.swiggy.MagicalArenaGame.Dice.AttackingDie;
import com.swiggy.MagicalArenaGame.Dice.DefendingDie;
import com.swiggy.MagicalArenaGame.Dice.Die;
import com.swiggy.MagicalArenaGame.Dice.SixSidedDie;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableDie;
import com.swiggy.MagicalArenaGame.Validations.DieValidator;

public class DiceFactory {

	public static enum Dice {
		ATTACKING_DIE, DEFENDING_DIE, SIX_SIDED_DIE, BASIC_DIE
	}

	private static DiceFactory instance;
	private final Map<Dice, PlayableDie> container;

	private DiceFactory() throws Exception {
		container = new HashMap<>();
		container.put(Dice.ATTACKING_DIE, new AttackingDie(new DieValidator()));
		container.put(Dice.DEFENDING_DIE, new DefendingDie(new DieValidator()));
		container.put(Dice.SIX_SIDED_DIE, new SixSidedDie(new DieValidator()));
	}

	public static synchronized DiceFactory getInstance() throws Exception {
		if (instance == null) {
			instance = new DiceFactory();
		}
		return instance;
	}

	public PlayableDie create(Dice diceType) {
		return container.get(diceType);
	}

	public PlayableDie createDieWithRange(int min, int max) throws Exception {
		return new Die(min, max, new DieValidator());
	}
}
