package com.renan.decoratorspacecraft.ui.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.renan.decoratorspacecraft.factory.SpacecraftFactory;
import com.renan.decoratorspacecraft.service.UpgradeService;

public class SpacecraftApplication extends Application {

    @Override
    public void start(Stage stage) {
        GamePreparationView preparationView = new GamePreparationView(
                new SpacecraftFactory(),
                new UpgradeService()
        );
        Scene scene = new Scene(preparationView.createContent(), 1000, 700);

        stage.setTitle("Decorator SpaceCraft");
        stage.setMinWidth(900);
        stage.setMinHeight(620);
        stage.setScene(scene);
        stage.show();
    }
}
