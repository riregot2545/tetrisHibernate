package com.tetris.db.repositories.impl;

import com.tetris.db.ConnectionFactory;
import com.tetris.db.entity.Game;
import com.tetris.db.entity.Move;
import com.tetris.db.repositories.Repository;
import com.tetris.game.handler.MoveEventType;
import org.hibernate.Session;

import java.util.ArrayDeque;
import java.util.Queue;

public class MoveRepository implements Repository {

    public void sameNewMoveEvent(Game game, MoveEventType moveEventType) {
        Move move = new Move();
        move.setMoveEventType(moveEventType);
        move.setGame(game);
        game.getMoves().add(move);

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(game);
            session.getTransaction().commit();
        }
    }

    public Queue<MoveEventType> getMoveEvents(Game game){
        Queue<MoveEventType> queue = new ArrayDeque<>();
        for (Move move : game.getMoves()) {
            queue.add(move.getMoveEventType());
        }
        return queue;
    }
}
