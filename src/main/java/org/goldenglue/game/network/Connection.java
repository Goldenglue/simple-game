package org.goldenglue.game.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class Connection {

    private static SocketChannel socketChannel;

    private static ConnectionHandler connectionHandler;

    public static void connect() {
        if (socketChannel == null) {
            try {
                socketChannel = SocketChannel.open();
                socketChannel.connect(new InetSocketAddress("localhost", 8080));
                connectionHandler = new ConnectionHandler();
                connectionHandler.handle(socketChannel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void disconnect() {
        if (socketChannel != null && socketChannel.isConnected()) {
            try {
                if (socketChannel.finishConnect()) {
                    connectionHandler.stop();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
