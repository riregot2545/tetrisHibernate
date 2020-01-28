package com.tetris.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tetris.db.entity.Game;
import com.tetris.game.Figure;
import com.tetris.model.Point;

import java.util.Queue;

public class FigureRestoreBuilder extends AbstractFigureBuilder {
    private final FigureBuilder builder;
    private final Queue<Figure.FigureBuilder> restoreFigures;

    public FigureRestoreBuilder(Game game, FigureBuilder builder) throws JsonProcessingException {
        super(game);
        this.builder = builder;
        this.restoreFigures = figureRepository.getFiguresByGame(game);
    }


    @Override
    public Figure next(Point boardStartPoint) {
        if(restoreFigures.isEmpty()){
            return builder.next(boardStartPoint);
        }else {
            return restoreFigures.poll()
                    .currentCoordinateOnBoard(boardStartPoint)
                    .build();
        }
    }
}
