package org.goldenglue.game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import org.goldenglue.game.network.Connection;

public class Game extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Connection.connect();
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(512, 512);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Player player = new Player(100, 100, 10, 10, 2);
        Animation animation = new Animation(gc, player);

        scene.setOnKeyPressed(new MovementEventHandler(animation));

        animation.start();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
