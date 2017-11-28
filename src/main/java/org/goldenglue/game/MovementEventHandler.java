package org.goldenglue.game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MovementEventHandler implements EventHandler<KeyEvent> {
    private Animation animation;

    public MovementEventHandler(Animation animation) {
        this.animation = animation;
    }

    @Override
    public void handle(KeyEvent e) {
        if (e.getCode().equals(KeyCode.RIGHT)) {
            animation.moveUserPixelOnX(5.0);
        }
        if (e.getCode().equals(KeyCode.LEFT)) {
            animation.moveUserPixelOnX(-5.0);
        }
        if (e.getCode().equals(KeyCode.UP)) {
            animation.moveUserPixelOnY(-5.0);
        }
        if (e.getCode().equals(KeyCode.DOWN)) {
            animation.moveUserPixelOnY(5.0);
        }
    }
}
