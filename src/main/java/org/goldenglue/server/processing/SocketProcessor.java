package org.goldenglue.server.processing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.goldenglue.server.ServerCommand;
import org.goldenglue.server.ServerMessage;
import org.goldenglue.server.Socket;
import org.goldenglue.server.gamelogic.GameState;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Set;

public class SocketProcessor implements Runnable {
    private final ByteBuffer buffer = ByteBuffer.allocate(256);
    private final Set<Socket> socketChannels;
    private final GameState gameState;
    private final ObjectMapper objectMapper;

    public SocketProcessor(Set<Socket> socketChannels, GameState gameState, ObjectMapper objectMapper) {
        this.socketChannels = socketChannels;
        this.gameState = gameState;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run() {
        while (true) {
            processingCycle();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processingCycle() {
        readFromSockets();
        writeIntoSockets();
    }

    private void readFromSockets() {

    }

    private void writeIntoSockets() {
        if (gameState.getPlayers().size() > 1) {
            ServerMessage<GameState> serverMessage = new ServerMessage<>(ServerCommand.GAME_STATE_SENT, gameState);
            try {
                byte[] bytes = objectMapper.writeValueAsBytes(serverMessage);
                buffer.put(bytes);
                buffer.flip();
                socketChannels.forEach(socket -> {
                    try {
                        socket.write(buffer);
                        buffer.flip();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                buffer.clear();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
