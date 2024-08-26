package com.swiggy.MagicalArenaGame.Validations;

import com.swiggy.MagicalArenaGame.Dice.AttackingDie;
import com.swiggy.MagicalArenaGame.Dice.DefendingDie;
import com.swiggy.MagicalArenaGame.Exceptions.InvalidPlayerAttributesException;
import com.swiggy.MagicalArenaGame.Exceptions.PlayerDiceMisMatchException;
import com.swiggy.MagicalArenaGame.Interfaces.Playable;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableDie;
import com.swiggy.MagicalArenaGame.Interfaces.PlayerValidatable;

public class PlayerValidator implements PlayerValidatable {

	@Override
	public void Validate(Playable player) throws InvalidPlayerAttributesException {
		ValidatePlayerAttributes(player);
	}

	@Override
	public void ValidatePlayerDice(PlayableDie attackingDie, PlayableDie defendingDie) throws PlayerDiceMisMatchException {
		if (!(attackingDie instanceof AttackingDie) || !(defendingDie instanceof DefendingDie)) {
			throw new PlayerDiceMisMatchException("Dice mismatch! Please check your attacking die and defending die!");
		}
	}

	@Override
	public void ValidatePlayerAttributes(Playable player) throws InvalidPlayerAttributesException {
		if (player.getHealth() <= 0 || player.getAttack() <= 0 || player.getStrength() <= 0) {
			throw new InvalidPlayerAttributesException(
					"Player attributes health, attack and strength must be positive!");
		}
	}
}