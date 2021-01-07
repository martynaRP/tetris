package com.epam.prejap.tetris.analyst;

import com.epam.prejap.tetris.game.Move;

/**
 * Analyses the command-line input
 * @author Martyna Rucińska Pereira
 */
public class CommandLineAnalyst {

    /**
     * Analyses the first string input from the command line
     * Checks if custom configuration for navigation keys was provided, if so, modifies them
     *
     * @param arg0  first string input from the command line
     * @return      navigation keys
     */
    public static char[] checkArgsForNavigationKeys(String arg0) {
        char[] keys = Move.defaultKeys();
        if (arg0 != null && !arg0.isBlank()) {
            char[] customKeys = transformArgToKeys(arg0);
            keys = Move.modifyNavigationKeys(customKeys);
            System.out.println("Custom navigation keys configured with success: \n" +
                    Move.navigationKeysConfiguration());
        }
        return keys;
    }

    private static char[] transformArgToKeys(String arg0) {
        String[] args = arg0.replaceAll("\\s+", " ").trim().split(" ");
        final int numberOfNavigationKeys = Move.values().length;
        if (args.length == numberOfNavigationKeys) {
            char[] keys = arg0.replaceAll("\\s", "").toCharArray();
            if (keys.length == numberOfNavigationKeys) {
                if (distinctValuesForNavigationKeys(keys)) {
                    return keys;
                } else throw new IllegalArgumentException("Each navigation key should be represented by a " +
                        "different character");
            } else throw new IllegalArgumentException("Each navigation key should be represented by a single " +
                    "character.");
        } else throw new IllegalArgumentException("Incorrect amount of values provided for navigation keys.");
    }

    private static boolean distinctValuesForNavigationKeys(char[] keys) {
        String keysAsString = new String(keys);
        long distinctCount = keysAsString.chars().distinct().count();
        return keys.length == distinctCount;
    }
}
