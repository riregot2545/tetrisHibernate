package com.tetris.db.repositories.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tetris.db.entity.FigureType;
import com.tetris.db.entity.Game;
import com.tetris.db.repositories.Repository;
import com.tetris.game.Figure;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.ArrayDeque;
import java.util.Queue;

public class FigureRepository implements Repository {

    private final FigureTypeRepository figureTypeRepository;

    public FigureRepository() throws JsonProcessingException {
        figureTypeRepository = new FigureTypeRepository();
    }

    public void saveNewFigure(Game game, Figure figure) {
        FigureType figureType = figureTypeRepository.convertToFigureType(figure);
        game.getFigures().add(figureType);

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(game);
            session.getTransaction().commit();
        }
    }

    public Queue<Figure.FigureBuilder> getFiguresByGame(Game game) {
        Queue<Figure.FigureBuilder> builderQueue = new ArrayDeque<>();
        try(Session session = sessionFactory.openSession()) {
            for (FigureType figureType : game.getFigures()) {
                builderQueue.add(figureTypeRepository.convertToFigureBuilder(figureType));
            }
        }
        return builderQueue;
    }
}
