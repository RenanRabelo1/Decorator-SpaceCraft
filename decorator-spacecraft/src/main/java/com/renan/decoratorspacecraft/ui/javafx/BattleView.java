package com.renan.decoratorspacecraft.ui.javafx;

import com.renan.decoratorspacecraft.domain.battle.EnemyShip;
import com.renan.decoratorspacecraft.domain.model.ShipStats;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import java.util.Objects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BattleView {

    private static final String BACKGROUND = "-fx-background-color: linear-gradient(to bottom, #150b29, #0b2344);";
    private static final String PLAYER_CARD = "-fx-background-color: #102f57; -fx-background-radius: 16;"
            + "-fx-border-color: #2fc5e6; -fx-border-radius: 16;";
    private static final String ENEMY_CARD = "-fx-background-color: #401a32; -fx-background-radius: 16;"
            + "-fx-border-color: #ef637d; -fx-border-radius: 16;";

    private final Spacecraft player;
    private final EnemyShip enemy;
    private final Runnable onBack;

    public BattleView(Spacecraft player, Runnable onBack) {
        this.player = Objects.requireNonNull(player);
        this.enemy = new EnemyShip();
        this.onBack = Objects.requireNonNull(onBack);
    }

    public BorderPane createContent() {
        BorderPane root = new BorderPane();
        root.setStyle(BACKGROUND);
        root.setPadding(new Insets(28, 42, 28, 42));

        root.setTop(createHeader());
        root.setCenter(createCombatants());
        root.setBottom(createFooter());
        return root;
    }

    private VBox createHeader() {
        Label eyebrow = new Label("MISSÃO 01  •  SETOR NEBULOSA");
        eyebrow.setStyle("-fx-text-fill: #d69cff; -fx-font-weight: bold;");

        Label title = new Label("PRONTO PARA A BATALHA");
        title.setStyle("-fx-text-fill: white;");
        title.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 30));

        Label subtitle = new Label("Sua configuração foi transferida da preparação para o campo de combate.");
        subtitle.setStyle("-fx-text-fill: #c8c2d5; -fx-font-size: 14px;");

        VBox header = new VBox(5, eyebrow, title, subtitle);
        header.setPadding(new Insets(0, 0, 22, 0));
        return header;
    }

    private HBox createCombatants() {
        VBox playerCard = combatantCard(
                "SUA NAVE",
                "✦",
                player.getDescription(),
                player.getStats(),
                PLAYER_CARD,
                "#64d8f2"
        );

        Label versus = new Label("VS");
        versus.setStyle("-fx-text-fill: #ffbd59; -fx-font-size: 28px; -fx-font-weight: bold;");

        VBox enemyCard = combatantCard(
                "ALVO INIMIGO",
                "◆",
                enemy.getStats().name(),
                enemy.getStats(),
                ENEMY_CARD,
                "#ef637d"
        );

        HBox combatants = new HBox(24, playerCard, versus, enemyCard);
        combatants.setAlignment(Pos.CENTER);
        HBox.setHgrow(playerCard, Priority.ALWAYS);
        HBox.setHgrow(enemyCard, Priority.ALWAYS);
        return combatants;
    }

    private VBox combatantCard(
            String cardTitle,
            String icon,
            String description,
            ShipStats stats,
            String cardStyle,
            String accentColor
    ) {
        Label title = new Label(cardTitle);
        title.setStyle("-fx-text-fill: " + accentColor + "; -fx-font-size: 12px; -fx-font-weight: bold;");

        Label iconLabel = new Label(icon);
        iconLabel.setStyle("-fx-text-fill: " + accentColor + "; -fx-font-size: 64px;");

        Label name = new Label(description);
        name.setWrapText(true);
        name.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        GridPane statsGrid = new GridPane();
        statsGrid.setHgap(10);
        statsGrid.setVgap(10);
        statsGrid.getColumnConstraints().addAll(column(), column());
        addStat(statsGrid, "VIDA", stats.health(), 0, 0, accentColor);
        addStat(statsGrid, "ATAQUE", stats.attack(), 1, 0, accentColor);
        addStat(statsGrid, "DEFESA", stats.defense(), 0, 1, accentColor);
        addStat(statsGrid, "VELOCIDADE", stats.speed(), 1, 1, accentColor);

        VBox card = new VBox(13, title, iconLabel, name, statsGrid);
        card.setStyle(cardStyle);
        card.setPadding(new Insets(24));
        card.setPrefWidth(410);
        card.setMaxWidth(Double.MAX_VALUE);
        return card;
    }

    private void addStat(
            GridPane grid,
            String label,
            int value,
            int column,
            int row,
            String accentColor
    ) {
        Label statName = new Label(label);
        statName.setStyle("-fx-text-fill: #b7c4d4; -fx-font-size: 10px; -fx-font-weight: bold;");
        Label statValue = new Label(String.valueOf(value));
        statValue.setStyle("-fx-text-fill: " + accentColor + "; -fx-font-size: 22px; -fx-font-weight: bold;");

        VBox stat = new VBox(3, statName, statValue);
        stat.setStyle("-fx-background-color: rgba(3, 14, 32, 0.42); -fx-background-radius: 10;");
        stat.setPadding(new Insets(12));
        stat.setMaxWidth(Double.MAX_VALUE);
        grid.add(stat, column, row);
    }

    private HBox createFooter() {
        Label message = new Label("Configuração confirmada. Os comandos de combate serão adicionados na próxima etapa.");
        message.setStyle("-fx-text-fill: #c8c2d5; -fx-font-size: 13px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button backButton = new Button("← AJUSTAR NAVE");
        backButton.setStyle("-fx-background-color: #274e7a; -fx-text-fill: white; -fx-font-weight: bold;"
                + "-fx-background-radius: 10; -fx-cursor: hand; -fx-padding: 13 22 13 22;");
        backButton.setOnAction(event -> onBack.run());

        HBox footer = new HBox(16, message, spacer, backButton);
        footer.setAlignment(Pos.CENTER_LEFT);
        footer.setPadding(new Insets(22, 0, 0, 0));
        return footer;
    }

    private ColumnConstraints column() {
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(50);
        return column;
    }
}
