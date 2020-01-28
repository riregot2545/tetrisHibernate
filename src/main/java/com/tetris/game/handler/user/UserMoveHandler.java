package com.tetris.game.handler.user;

import com.tetris.db.entity.Game;
import com.tetris.db.repositories.impl.MoveRepository;
import com.tetris.game.handler.MoveEventType;
import com.tetris.game.handler.MoveHandler;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class UserMoveHandler implements MoveHandler {
    private final Game game;
    private final PlayerMoveEventPool moveEventPool = new PlayerMoveEventPool();

    @Override
    public MoveEventType getNewMoveEvent() {
        Scanner scanner = new Scanner(System.in);
        MoveEventType event;
        do {
            event = moveEventPool.pool.get(scanner.nextLine());
            moveRepository.sameNewMoveEvent(game, event);
        } while (event == null);
        return event;
    }
}
