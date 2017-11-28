package org.goldenglue.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Queue;

public class SocketAccepter implements Runnable {
    private int port;
    private ServerSocketChannel serverSocketChannel;
    private Queue<Socket> socketChannels;
    private long newSocketId;
    private ByteBuffer buffer = ByteBuffer.allocate(256);
    private ObjectMapper objectMapper;
    private GameState gameState;


    public SocketAccepter(int port, Queue<Socket> socketChannels, ObjectMapper objectMapper, GameState gameState) {
        this.port = port;
        this.socketChannels = socketChannels;
        this.objectMapper = objectMapper;
        this.gameState = gameState;
    }

    @Override
    public void run() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                Socket socket = new Socket(newSocketId++, socketChannel);
                System.out.println("New user accepted with id: " + socket.getSocketId());
                socketChannels.add(socket);
                onNewUser(socket);
                Thread.sleep(5);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void onNewUser(Socket socket) throws JsonProcessingException {
        Player player;
        if (socket.getSocketId() == 0) {
            player = new Player(socket,100,100,10,10, socket.getSocketId());
        } else {
            player = new Player(socket,200,200,10,10, socket.getSocketId());
        }

        gameState.addPlayerIntoGame(player);
        System.out.println(objectMapper.writeValueAsString(gameState));
        byte[] bytes = objectMapper.writeValueAsBytes(gameState);
        System.out.println(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        try {
            socket.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer.clear();
    }
}
