package org.goldenglue.server;

public class ServerMessage<T> {
    private ServerCommand command;
    private T object;

    public ServerMessage(ServerCommand command, T object) {
        this.command = command;
        this.object = object;
    }

    public ServerCommand getCommand() {
        return command;
    }

    public void setCommand(ServerCommand command) {
        this.command = command;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
