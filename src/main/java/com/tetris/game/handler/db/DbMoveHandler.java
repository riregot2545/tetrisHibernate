package com.tetris.game.handler.db;

import com.tetris.db.entity.Game;
import com.tetris.game.handler.MoveEventType;
import com.tetris.game.handler.MoveHandler;
import com.tetris.game.handler.user.UserMoveHandler;

import java.util.ArrayDeque;
import java.util.Queue;

public class DbMoveHandler implements MoveHandler {

    private final UserMoveHandler userMoveHandler;
    private Queue<MoveEventType> moveEventQueue;

    public DbMoveHandler(Game restoreGame) {
        userMoveHandler = new UserMoveHandler(restoreGame);
        moveEventQueue = moveRepository.getMoveEvents(restoreGame);
    }

    @Override
    public MoveEventType getNewMoveEvent() {
        if(moveEventQueue.isEmpty()){
            return userMoveHandler.getNewMoveEvent();
        }else {
            return moveEventQueue.poll();
        }
    }
}
