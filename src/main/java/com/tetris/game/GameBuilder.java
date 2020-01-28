package com.tetris.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tetris.builder.FigureBuilderFactory;
import com.tetris.db.repositories.impl.GameRepository;
import com.tetris.game.handler.db.DbMoveHandler;
import com.tetris.game.handler.user.UserMoveHandler;
import com.tetris.model.GameState;

import java.util.Optional;

public class GameBuilder {

    private static int HEIGHT = 15;
    private static int WIDTH = 10;

    private static final GameRepository gameRepository = new GameRepository();
    public static Game build() throws JsonProcessingException {
        Optional<com.tetris.db.entity.Game> game = gameRepository.getLastGame();
        if(game.isPresent() && !game.get().getState().equals(GameState.FINISH)){
            return restoreGame(game.get());
        }else {
            return buildNewGame();
        }
    }

    private static Game restoreGame(com.tetris.db.entity.Game game) throws JsonProcessingException {

        Board board = new Board(HEIGHT, WIDTH, new FigureBuilderFactory().getRestoreBuilder(game));
        return new Game(new DbMoveHandler(game), board, gameRepository);
    }

    private static Game buildNewGame() throws JsonProcessingException {
        com.tetris.db.entity.Game game = gameRepository.createNewGame();
        Board board = new Board(HEIGHT, WIDTH, new FigureBuilderFactory().getClassicBuilder(game));
        return new Game(new UserMoveHandler(game), board, gameRepository);
    }
}
