package org.goldenglue.server;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonClassDescription
public class Player {
    @JsonIgnore
    private Socket playersSocket;
    private double x;
    private double y;
    private double w;
    private double h;
    private long number;

    public Player(Socket playersSocket, double x, double y, double w, double h, long number) {
        this.playersSocket = playersSocket;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.number = number;
    }

    public Socket getPlayersSocket() {
        return playersSocket;
    }

    public void setPlayersSocket(Socket playersSocket) {
        this.playersSocket = playersSocket;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

}
