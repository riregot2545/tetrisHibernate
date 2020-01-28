package com.tetris.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tetris.db.entity.Game;
import com.tetris.db.repositories.impl.FigureRepository;
import com.tetris.db.repositories.impl.FigureTypeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;


public abstract class AbstractFigureBuilder implements FigureBuilder {
    final FigureRepository figureRepository = new FigureRepository();
    final FigureTypeRepository figureTypeRepository = new FigureTypeRepository();

    public AbstractFigureBuilder(Game game) throws JsonProcessingException {
        this.game = game;
    }


    @Getter
    private final Game game;
}
