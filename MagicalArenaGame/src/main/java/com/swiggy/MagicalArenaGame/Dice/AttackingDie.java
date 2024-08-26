package com.swiggy.MagicalArenaGame.Dice;

import com.swiggy.MagicalArenaGame.Interfaces.PlayableDie;
import com.swiggy.MagicalArenaGame.Interfaces.Validatable;

public class AttackingDie extends SixSidedDie {
	public AttackingDie(Validatable<PlayableDie> dieValidator) throws Exception {
		super(dieValidator);
	}
}