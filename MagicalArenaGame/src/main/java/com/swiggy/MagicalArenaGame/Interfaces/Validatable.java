package com.swiggy.MagicalArenaGame.Interfaces;

public interface Validatable<T> {
	void Validate(T validationObj) throws Exception;
}
