package com.tetris.builder;

import com.tetris.game.Figure;
import com.tetris.model.Point;

public interface FigureBuilder {

    Figure next(Point boardStartPoint);

}
