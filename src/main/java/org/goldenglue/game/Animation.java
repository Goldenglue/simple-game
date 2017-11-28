package org.goldenglue.game;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Animation extends AnimationTimer {
    private GraphicsContext gc;
    private Player player;


    public Animation(GraphicsContext gc, Player player) {
        this.gc = gc;
        this.player = player;
    }

    @Override
    public void handle(long now) {
        gc.setFill(new Color(0.9843, 1, 1, 1));
        gc.fillRect(0, 0, 512, 512);
        drawUserPixel(player);
    }

    private void drawUserPixel(Player pixel) {

        gc.setFill(pixel.getColor());
        gc.fillRect(pixel.getX(), pixel.getY(), pixel.getW(), pixel.getH());
    }

    public void moveUserPixelOnX(double distance) {
        player.setX(player.getX() + distance);
    }

    public void moveUserPixelOnY(double distance) {
        player.setY(player.getY() + distance);
    }
}
