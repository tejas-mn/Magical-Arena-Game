package com.swiggy.MagicalArenaGame.Dice;

import com.swiggy.MagicalArenaGame.Interfaces.PlayableDie;
import com.swiggy.MagicalArenaGame.Interfaces.Validatable;

public class DefendingDie extends SixSidedDie {
	public DefendingDie(Validatable<PlayableDie> dieValidator) throws Exception {
		super(dieValidator);
	}
}