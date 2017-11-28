package org.goldenglue.game.network.connection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.goldenglue.game.Animation;
import org.goldenglue.game.GameState;
import org.goldenglue.game.Player;
import org.goldenglue.game.network.server.ServerCommand;
import org.goldenglue.game.network.server.ServerMessageTranslator;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionHandler {
    private SocketChannel socketChannel;
    private ByteBuffer buffer = ByteBuffer.allocate(256);
    private ExecutorService executorService;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Animation animation;
    private ServerMessageTranslator serverMessageTranslator;
    private Runnable connectionLoop = () -> {
        while (true) {
            read();
            write();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public ConnectionHandler(Animation animation) {
        this.animation = animation;
    }

    public void handle(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        this.executorService = Executors.newSingleThreadExecutor();
        this.serverMessageTranslator = new ServerMessageTranslator(objectMapper);
        this.executorService.submit(connectionLoop);
    }

    private void read() {
        try {
            int bytesRead = socketChannel.read(buffer);
            byte[] bytes = new byte[bytesRead];
            buffer.flip();
            int start = 0;
            while (buffer.hasRemaining()) {
                bytes[start++] = buffer.get();
            }
            //System.out.println(new String(bytes, Charset.forName("UTF-8")));
            ServerCommand serverCommand = serverMessageTranslator.translateAndGetCommand(bytes);
            processCommand(serverCommand, bytes);


            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write() {

    }


    public void stop() {
        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }

    private void processCommand(ServerCommand command, byte[] bytes) {
        switch (command) {
            case PLAYER_CONNECTED:
                onConnection(bytes);
                break;
            case GAME_STATE_SENT:
                onGameState(bytes);
                break;
        }
    }

    private void onConnection(byte[] bytes) {
        System.out.println("on connection");
        try {
            JsonNode jsonNode = objectMapper.readTree(bytes);
            if (jsonNode.hasNonNull("object")) {
                JsonNode objectNode = jsonNode.get("object");
                Player player = objectMapper.treeToValue(objectNode, Player.class);
                animation.setPlayer(player);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onGameState(byte[] bytes) {
        System.out.println("on game state");
        try {
            JsonNode jsonNode = objectMapper.readTree(bytes);
            if (jsonNode.hasNonNull("object")) {
                JsonNode objectNode = jsonNode.get("object");
                GameState gameState = objectMapper.treeToValue(objectNode, GameState.class);
                animation.updateGameState(gameState);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
