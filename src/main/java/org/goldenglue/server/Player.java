package org.goldenglue.server;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.paint.Color;

@JsonClassDescription
public class Player {
    @JsonIgnore
    private Socket playersSocket;
    private double x;
    private double y;
    private double w;
    private double h;
    private int number;
    private Color color;

    public Player(Socket playersSocket, int number) {
        this.playersSocket = playersSocket;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
