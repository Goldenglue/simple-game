package org.goldenglue.game;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.paint.Color;

@JsonClassDescription
public class Player {
    private double x;
    private double y;
    private double w;
    private double h;
    private long number;
    @JsonIgnore
    private Color color;

    public Player(double x, double y, double w, double h, int number) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.number = number;
    }

    public Player() {

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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


}
