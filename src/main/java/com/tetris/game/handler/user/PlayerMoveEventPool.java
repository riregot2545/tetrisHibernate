package com.tetris.game.handler.user;

import com.tetris.game.handler.MoveEventType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.tetris.game.handler.MoveEventType.*;

public class PlayerMoveEventPool {
    public final Map<String, MoveEventType> pool;

    public PlayerMoveEventPool() {
        Map<String, MoveEventType> pool = new HashMap<>();
        pool.put("a", MOVE_LEFT);
        pool.put("s", MOVE_DOWN);
        pool.put("d", MOVE_RIGHT);
        pool.put("q", LEFT_ROTATE);
        pool.put("e", RIGHT_ROTATE);
        this.pool = Collections.unmodifiableMap(pool);
    }
}
