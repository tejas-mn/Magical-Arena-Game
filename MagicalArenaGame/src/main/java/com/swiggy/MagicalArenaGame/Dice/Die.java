package com.swiggy.MagicalArenaGame.Dice;

import com.swiggy.MagicalArenaGame.Interfaces.PlayableDie;
import com.swiggy.MagicalArenaGame.Interfaces.Validatable;

import java.util.Random;

public class Die implements PlayableDie {

	private final int minRange;
	private final int maxRange;
	private final Random random;
	private final Validatable<PlayableDie> dieValidator;

	public Die(int minRange, int maxRange, Validatable<PlayableDie> dieValidator) throws Exception {
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.random = new Random();
		this.dieValidator = dieValidator;
		this.dieValidator.Validate(this);
	}

	@Override
	public int getMinRange() {
		return this.minRange;
	}

	@Override
	public int getMaxRange() {
		return this.maxRange;
	}

	@Override
	public int roll() {
		return random.nextInt(maxRange - minRange + 1) + minRange;
	}
}