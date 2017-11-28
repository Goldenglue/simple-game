package org.goldenglue.game.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.goldenglue.game.Animation;

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
            ServerCommand serverCommand = serverMessageTranslator.translate(bytes);

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
}
