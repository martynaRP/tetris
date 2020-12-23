package com.epam.prejap.tetris.game;

import java.util.concurrent.TimeUnit;

public class Waiter {

    private final int milliseconds;

    public Waiter(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public void waitForIt() {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException ignore) {
        }
    }
}
