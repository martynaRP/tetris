package com.epam.prejap.tetris.player;

import com.epam.prejap.tetris.game.Move;
import java.util.Optional;

public interface Player {

    Optional<Move> nextMove();

}
