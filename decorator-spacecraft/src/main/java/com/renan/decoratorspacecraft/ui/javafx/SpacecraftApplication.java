package com.renan.decoratorspacecraft.ui.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SpacecraftApplication extends Application {

    @Override
    public void start(Stage stage) {
        Label title = new Label("DECORATOR SPACECRAFT");
        Scene scene = new Scene(new StackPane(title), 900, 600);

        stage.setTitle("Decorator SpaceCraft");
        stage.setScene(scene);
        stage.show();
    }
}
