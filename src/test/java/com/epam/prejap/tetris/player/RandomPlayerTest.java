package com.epam.prejap.tetris.player;

import com.epam.prejap.tetris.game.Move;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RandomPlayerTest {

    private static final Player testingRandomizer = new RandomPlayer();

    @DataProvider
    public Object[] allMoveEnumValues(){
        return Move.values();
    }

    @Test(groups = "Student", dataProvider = "allMoveEnumValues")
    public void nextMoveShouldGenerateEachPossibleValue(Move move) {
        Move expected;
        do {
            expected= testingRandomizer.nextMove().get();
        } while(!expected.equals(move));
        assertEquals(expected,move);
    }


}