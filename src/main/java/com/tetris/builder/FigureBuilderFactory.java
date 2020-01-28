package com.tetris.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tetris.db.entity.Game;

public class FigureBuilderFactory {


    public FigureBuilder getClassicBuilder(Game game) throws JsonProcessingException {
        return new FigureClassicBuilder(game);
    }

    public FigureBuilder getRestoreBuilder(Game game) throws JsonProcessingException {
        return new FigureRestoreBuilder(game, new FigureClassicBuilder(game));
    }
}
