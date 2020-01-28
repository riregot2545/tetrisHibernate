package com.tetris.db.repositories.impl;

import com.tetris.db.entity.FigureType;
import com.tetris.db.entity.Game;
import com.tetris.db.repositories.Repository;
import com.tetris.model.GameState;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class GameRepository implements Repository {

    private Game activeGame;

    public Game getActiveGame() {
        return activeGame;
    }

    public Game createNewGame() {
        Game game = new Game();
        game.setState(GameState.ACTIVE);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(game);
        session.getTransaction().commit();

        activeGame = game;

        return game;
    }

    public Optional<Game> getLastGame(){
        try(Session session = sessionFactory.openSession()) {
            List<Game> resList = session.createQuery("FROM Game ORDER BY gameId DESC", Game.class)
                    .setMaxResults(1)
                    .list();
            if (resList.isEmpty())
                return Optional.empty();
            else {
                Game gm = resList.get(0);
                activeGame = gm;
                Hibernate.initialize(gm.getMoves());
                Hibernate.initialize(gm.getFigures());
                return Optional.of(gm);
            }

        }
    }

    public void finishGame(Game game) {
        game.setState(GameState.FINISH);

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(game);
            session.getTransaction().commit();
        }
    }

}
