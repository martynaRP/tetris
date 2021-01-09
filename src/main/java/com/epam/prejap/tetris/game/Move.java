package com.epam.prejap.tetris.game;

import java.util.Arrays;

/**
 * Stores moves available in the game with the keys that are used to perform each move.
 */
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

    /**
     * Provides a set of default navigation keys.
     * @return array of navigation keys for all moves
     */
    public static char[] defaultKeys() {
        return new char[]{' ', 'h', 'l'};
    }

    /**
     * Modifies navigation keys.
     * Replaces default setting with custom navigation keys.
     * @param keys  custom configuration
     * @return      modified navigation keys
     */
    public static char[] modifyNavigationKeys(char[] keys) {
        Arrays.stream(values()).forEach(val -> val.key = keys[val.ordinal()]);
        return Move.getNavigationKeys();
    }

    private static char[] getNavigationKeys() {
        char[] navigationKeys = new char[values().length];
        Arrays.stream(values()).forEach(val -> navigationKeys[val.ordinal()] = (char) val.key);
        return navigationKeys;
    }

    /**
     * Provides current configuration of navigation keys.
     * @return string representation of moves and associated keys
     */
    public static String navigationKeysConfiguration() {
        return Arrays.deepToString(Arrays.stream(values())
                .map(val -> "" + val + ": " + (char) val.key).toArray());
    }
}
