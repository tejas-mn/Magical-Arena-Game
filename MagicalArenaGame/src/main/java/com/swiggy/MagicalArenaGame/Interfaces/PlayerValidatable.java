package com.swiggy.MagicalArenaGame.Interfaces;

public interface PlayerValidatable extends Validatable<Playable> {

	void Validate(Playable player) throws Exception;

	void ValidatePlayerDice(PlayableDie attackingDie, PlayableDie defendingDie) throws Exception;

	void ValidatePlayerAttributes(Playable player) throws Exception;
}