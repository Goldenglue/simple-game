package org.goldenglue.server;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private SocketAccepter socketAccepter;
    private SocketProcessor socketProcessor;
    private ExecutorService executorService;
    private ObjectMapper objectMapper;
    private GameState gameState;

    public Server(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        Server server = new Server(8080);
        server.start();
    }

    public void start() {
        Queue<Socket> socketChannels = new ArrayBlockingQueue<>(1024);
        this.objectMapper = new ObjectMapper();
        this.gameState = new GameState();
        this.socketAccepter = new SocketAccepter(port, socketChannels, objectMapper, gameState);

        this.executorService = Executors.newFixedThreadPool(2);
        executorService.submit(socketAccepter);

        this.socketProcessor = new SocketProcessor();
        executorService.submit(socketProcessor);

        System.out.println("Server started on port " + port);
    }
}
