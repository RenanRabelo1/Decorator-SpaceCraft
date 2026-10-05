package com.renan.decoratorspacecraft.ui.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import com.renan.decoratorspacecraft.factory.SpacecraftFactory;
import com.renan.decoratorspacecraft.service.UpgradeService;

public class SpacecraftApplication extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Decorator SpaceCraft");
        stage.setMinWidth(900);
        stage.setMinHeight(620);
        showPreparation(stage);
        stage.show();
    }

    private void showPreparation(Stage stage) {
        GamePreparationView preparationView = new GamePreparationView(
                new SpacecraftFactory(),
                new UpgradeService(),
                spacecraft -> showBattle(stage, spacecraft)
        );
        stage.setScene(new Scene(preparationView.createContent(), 1000, 700));
    }

    private void showBattle(Stage stage, Spacecraft spacecraft) {
        BattleView battleView = new BattleView(spacecraft, () -> showPreparation(stage));
        stage.setScene(new Scene(battleView.createContent(), 1000, 700));
    }
}
