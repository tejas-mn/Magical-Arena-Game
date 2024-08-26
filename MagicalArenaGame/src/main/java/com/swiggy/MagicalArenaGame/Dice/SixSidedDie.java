package com.swiggy.MagicalArenaGame.Dice;

import com.swiggy.MagicalArenaGame.Interfaces.PlayableDie;
import com.swiggy.MagicalArenaGame.Interfaces.Validatable;

public class SixSidedDie extends Die {

	private final static int minValue = 1;
	private final static int maxValue = 6;

	public SixSidedDie(Validatable<PlayableDie> dieValidator) throws Exception {
		super(minValue, maxValue, dieValidator);
	}
}