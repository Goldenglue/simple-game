package org.goldenglue.server;

import java.nio.ByteBuffer;

public class SocketProcessor implements Runnable {
    private static final ByteBuffer buffer = ByteBuffer.allocate(64);

    @Override
    public void run() {
        while (true) {
            processingCycle();
            try {
                Thread.sleep(10);
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
    }
}
