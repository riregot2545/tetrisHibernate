package com.tetris.game.handler;

import com.tetris.db.repositories.impl.MoveRepository;

public interface MoveHandler {

    MoveRepository moveRepository = new MoveRepository();

    MoveEventType getNewMoveEvent();
}
