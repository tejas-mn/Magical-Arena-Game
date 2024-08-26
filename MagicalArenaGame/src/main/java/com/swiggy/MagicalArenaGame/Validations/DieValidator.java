package com.swiggy.MagicalArenaGame.Validations;

import com.swiggy.MagicalArenaGame.Exceptions.InvalidDieRangeException;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableDie;
import com.swiggy.MagicalArenaGame.Interfaces.Validatable;

public class DieValidator implements Validatable<PlayableDie> {
	@Override
	public void Validate(PlayableDie die) throws InvalidDieRangeException {
		if (die.getMinRange() <= 0 || die.getMaxRange() <= 0) {
			throw new InvalidDieRangeException("Min and Max Range for Die should be positive!");
		}
	}
}
