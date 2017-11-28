package org.goldenglue.game.network.server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.goldenglue.game.network.server.ServerCommand;

import java.io.IOException;

public class ServerMessageTranslator {
    private ObjectMapper mapper;

    public ServerMessageTranslator(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public ServerCommand translateAndGetCommand(byte[] bytes) {
        try {
            JsonNode jsonNode = mapper.readTree(bytes);
            System.out.println(jsonNode.asText());
            if (jsonNode.hasNonNull("command")) {
                int command = jsonNode.get("command").asInt();
                switch (command) {
                    case 0: return ServerCommand.PLAYER_CONNECTED;
                    case 1: return ServerCommand.ANOTHER_PLAYER_CONNECTED;
                    case 2: return ServerCommand.GAME_STATE_SENT;
                    case 3: return ServerCommand.PLAYER_LEFT;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ServerCommand.UNKNOWN_COMMAND;
    }

    public String translateAndGetMessage(byte[] bytes) {
        try {
            JsonNode jsonNode = mapper.readTree(bytes);
            if (jsonNode.hasNonNull("object")) {
                return jsonNode.get("object").asText();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
