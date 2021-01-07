package com.epam.prejap.tetris;

import com.epam.prejap.tetris.block.BlockFeed;
import com.epam.prejap.tetris.game.Move;
import com.epam.prejap.tetris.game.Playfield;
import com.epam.prejap.tetris.game.Printer;
import com.epam.prejap.tetris.game.Waiter;
import com.epam.prejap.tetris.player.Player;
import com.epam.prejap.tetris.player.RandomPlayer;

import java.util.Random;

class Tetris {

    private final Playfield playfield;
    private final Waiter waiter;
    private final Player player;

    public Tetris(Playfield playfield, Waiter waiter, Player player) {
        this.playfield = playfield;
        this.waiter = waiter;
        this.player = player;
    }

    public Score play() {
        boolean moved;
        int score = 0;
        do {
            moved = false;

            playfield.nextBlock();
            score++;

            boolean nextMove;
            do {
                waiter.waitForIt();
                Move move = player.nextMove().orElse(Move.NONE);
                moved |= (nextMove = playfield.move(move));
            } while (nextMove);

        } while (moved);

        return new Score(score);
    }

    public static void main(String[] args) {
        int rows = 10;
        int cols = 20;
        int delay = 500;

        var feed = new BlockFeed();
        var printer = new Printer(System.out);
        var playfield = new Playfield(rows, cols, feed, printer);
        var game = new Tetris(playfield, new Waiter(delay), new RandomPlayer(new Random()));

        if (args != null && args.length != 0) {
            checkArgsForNavigationKeys(args[0]);
        }

        var score = game.play();

        System.out.println("Score: " + score.points());
    }

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
                return keys;
            } else throw new IllegalArgumentException("Each navigation key should be represented by a single " +
                    "character.");
        } else throw new IllegalArgumentException("Incorrect amount of values provided for navigation keys.");
    }
}
