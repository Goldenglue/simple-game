package org.goldenglue.server;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ServerCommand {
    PLAYER_CONNECTED(0),
    ANOTHER_PLAYER_CONNECTED(1),
    GAME_STATE_SENT(2),
    PLAYER_LEFT(3);

    private final int code;

    ServerCommand(int code) {
        this.code = code;
    }

    @JsonValue
    public int getCode() {
        return code;
    }
}
