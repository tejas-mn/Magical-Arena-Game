package com.swiggy.MagicalArenaGame.Exceptions;

public class PlayerLimitReachedException extends Exception {
    public PlayerLimitReachedException(String message) {
        super(message);
    }
}