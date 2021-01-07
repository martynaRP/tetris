package com.epam.prejap.tetris.game;

import java.util.Arrays;

public enum Move {

    NONE(defaultKeys()[0]),
    LEFT(defaultKeys()[1]),
    RIGHT(defaultKeys()[2]),
    ;

    private int key;

    Move(int key) {
        this.key = key;
    }

    public static Move of(int key) {
        for (var move : values()) {
            if (move.key == key) {
                return move;
            }
        }
        return NONE;
    }

    public static char[] defaultKeys() {
        return new char[]{' ', 'h', 'l'};
    }

    public static char[] modifyNavigationKeys(char[] keys) {
        Arrays.stream(values()).forEach(val -> val.key = keys[val.ordinal()]);
        return Move.getNavigationKeys();
    }

    private static char[] getNavigationKeys() {
        char[] navigationKeys = new char[values().length];
        Arrays.stream(values()).forEach(val -> navigationKeys[val.ordinal()] = (char) val.key);
        return navigationKeys;
    }

    public static String navigationKeysConfiguration() {
        return Arrays.deepToString(Arrays.stream(values())
                .map(val -> "" + val + ": " + (char) val.key).toArray());
    }
}
