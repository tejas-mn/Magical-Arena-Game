package com.swiggy.MagicalArenaGame.Interfaces;

public interface Playable extends PlayableAttributes {
	int attack();

	int defend();

	boolean isAlive();

	void decreaseHealth(int damagedHealth);
}