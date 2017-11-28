package org.goldenglue.game.network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionHandler {
    private SocketChannel socketChannel;
    private ByteBuffer buffer = ByteBuffer.allocate(64);
    private ExecutorService executorService;
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

    public void handle(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        this.executorService = Executors.newSingleThreadExecutor();
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
            System.out.println(new String(bytes, Charset.forName("UTF-8")));
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
