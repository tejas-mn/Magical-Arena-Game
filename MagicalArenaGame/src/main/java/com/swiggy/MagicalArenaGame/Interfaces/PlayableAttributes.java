package com.swiggy.MagicalArenaGame.Interfaces;

public interface PlayableAttributes {
	int getHealth();

	int getAttack();

	int getStrength();

	int getId();
	
	void resetPlayerAttributes();
}