package org.goldenglue.game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import org.goldenglue.game.network.connection.Connection;

public class Game extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(512, 512);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Animation animation = new Animation(gc);
        Connection connection = new Connection(animation);
        connection.connect();


        scene.setOnKeyPressed(new MovementEventHandler(animation));

        animation.start();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
