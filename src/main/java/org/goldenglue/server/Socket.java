package org.goldenglue.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Socket {
    private long socketId;
    private SocketChannel socketChannel;
    private boolean isDoneStreaming;

    public Socket(long socketId, SocketChannel socketChannel) {
        this.socketId = socketId;
        this.socketChannel = socketChannel;
    }

    public int read(ByteBuffer buffer) throws IOException {
        int bytesRead = socketChannel.read(buffer);
        int totalBytesRead = bytesRead;

        while (bytesRead > 0) {
            bytesRead = socketChannel.read(buffer);
            totalBytesRead += bytesRead;
        }
        if (bytesRead == -1) {
            isDoneStreaming = true;
        }

        return totalBytesRead;
    }

    public int write(ByteBuffer buffer) throws IOException {
        int bytesWritten = socketChannel.write(buffer);
        int totalBytesWritten = bytesWritten;

        while (bytesWritten > 0 && buffer.hasRemaining()) {
            bytesWritten = socketChannel.write(buffer);
            totalBytesWritten += bytesWritten;
        }

        return totalBytesWritten;
    }

    public long getSocketId() {
        return socketId;
    }

    public void setSocketId(long socketId) {
        this.socketId = socketId;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public boolean isDoneStreaming() {
        return isDoneStreaming;
    }

    public void setDoneStreaming(boolean doneStreaming) {
        isDoneStreaming = doneStreaming;
    }
}
