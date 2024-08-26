package com.swiggy.MagicalArenaGame.Utility;

import com.swiggy.MagicalArenaGame.Interfaces.Playable;
import com.swiggy.MagicalArenaGame.Interfaces.PlayableSwappable;

public class PlayableSwapper extends GenericSwapper<Playable> implements PlayableSwappable {

	public PlayableSwapper(Playable firstPlayer, Playable secondPlayer) {
		super(firstPlayer, secondPlayer);
	}

	@Override
	public Playable getFirstPlayable() {
		return super.getFirstItem();
	}

	@Override
	public Playable getSecondPlayable() {
		return super.getSecondItem();
	}

	@Override
	public void swapPlayables() {
		super.swap();
	}
}
