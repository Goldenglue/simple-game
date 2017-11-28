package org.goldenglue.server;

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
    private ByteBuffer buffer = ByteBuffer.allocate(128);


    public SocketAccepter(int port, Queue<Socket> socketChannels) {
        this.port = port;
        this.socketChannels = socketChannels;
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
                System.out.println("New user accepted: " + socketChannel.getLocalAddress());
                Socket socket = new Socket(newSocketId++, socketChannel);
                onNewUser(socket);
                socketChannels.add(socket);
                Thread.sleep(5);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void onNewUser(Socket socket) {
        buffer.put(("Hello user " + socket.getSocketId()).getBytes());
        buffer.flip();
        try {
            socket.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
