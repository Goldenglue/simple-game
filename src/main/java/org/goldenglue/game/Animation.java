package org.goldenglue.game;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Animation extends AnimationTimer {
    private GraphicsContext gc;
    private GameState gameState = new GameState();
    private Player player;


    public Animation(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void handle(long now) {
        gc.setFill(new Color(0.0863, 1, 0.0863, 1));
        gc.fillRect(0, 0, 512, 512);
        drawPlayersPixels();
    }

    private void drawPlayersPixels() {
        gameState.getPlayers().forEach(player -> {
            gc.setFill(player.getColor());
            gc.fillRect(player.getX(), player.getY(), player.getW(), player.getH());
        });
    }

    public void updateGameState(GameState gameState) {
        gameState.getPlayers().forEach(player -> {
            if (player.getNumber() == 0) {
                player.setColor(Color.YELLOW);
            } else {
                player.setColor(Color.RED);
            }
        });
        this.gameState = gameState;
    }

    public void setPlayer(Player player) {
        if (player.getNumber() == 0) {
            player.setColor(Color.YELLOW);
        } else {
            player.setColor(Color.RED);
        }
        gameState.addPlayerIntoGame(player);
        this.player = player;
    }

    public void moveUserPixelOnX(double distance) {
        player.setX(player.getX() + distance);
    }

    public void moveUserPixelOnY(double distance) {
        player.setY(player.getY() + distance);
    }
}
