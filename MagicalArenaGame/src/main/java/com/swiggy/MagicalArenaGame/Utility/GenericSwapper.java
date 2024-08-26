package com.swiggy.MagicalArenaGame.Utility;

import com.swiggy.MagicalArenaGame.Interfaces.GenericSwappable;

public abstract class GenericSwapper<T> implements GenericSwappable<T> {
	private T first;
	private T second;

	public GenericSwapper(T firstItem, T secondItem) {
		this.first = firstItem;
		this.second = secondItem;
	}

	@Override
	public void swap() {
		T temp = this.first;
		this.first = this.second;
		this.second = temp;
	}

	@Override
	public T getFirstItem() {
		return this.first;
	}

	@Override
	public T getSecondItem() {
		return this.second;
	}
}
