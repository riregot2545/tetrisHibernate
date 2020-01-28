package com.tetris.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tetris.db.entity.Game;
import com.tetris.game.Figure;
import com.tetris.model.Point;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Random;

public class FigureClassicBuilder extends AbstractFigureBuilder {

    private final Random random = new Random();

    public FigureClassicBuilder(Game game) throws JsonProcessingException {
        super(game);
    }

    @Override
    public Figure next(Point boardStartPoint) {

        Figure randomFigure = figureTypeRepository
                .getFigureTemplates()
                .get(random.nextInt(figureTypeRepository.getFigureTemplates().size()))
                .currentCoordinateOnBoard(boardStartPoint)
                .build();
        figureRepository.saveNewFigure(getGame(),randomFigure);
        return randomFigure;
    }
}
