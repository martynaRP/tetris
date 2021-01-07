package com.epam.prejap.tetris;

import com.epam.prejap.tetris.analyst.CommandLineAnalyst;
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

    /**
     * Prepares the environment and launches the game
     *
     * @param args  array of strings input from the command line
     *              args[0] dedicated to configure custom navigation keys
     *                      each key should be represented by a single character and separated by space
     *                      input example: "q s d" -> none: q, left: s, right: d
     */
    public static void main(String[] args) {
        int rows = 10;
        int cols = 20;
        int delay = 500;

        var feed = new BlockFeed();
        var printer = new Printer(System.out);
        var playfield = new Playfield(rows, cols, feed, printer);
        var game = new Tetris(playfield, new Waiter(delay), new RandomPlayer(new Random()));

        if (args != null && args.length != 0) {
            CommandLineAnalyst.checkArgsForNavigationKeys(args[0]);
        }

        var score = game.play();

        System.out.println("Score: " + score.points());
    }
}
