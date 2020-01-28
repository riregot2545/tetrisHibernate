package com.tetris.game;

import com.tetris.db.repositories.impl.GameRepository;
import com.tetris.game.handler.MoveHandler;
import com.tetris.model.GameState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.tetris.model.GameState.ACTIVE;

@AllArgsConstructor
@Slf4j
public class Game {

    private final MoveHandler moveHandler;
    private final Board board;

    private final GameRepository gameRepository;
    public void start() {
        GameState state = ACTIVE;
        while (state == ACTIVE) {
            state = board.doGame(moveHandler.getNewMoveEvent());
        }
        log.info("Finish game");
        gameRepository.finishGame(gameRepository.getActiveGame());
    }
}
