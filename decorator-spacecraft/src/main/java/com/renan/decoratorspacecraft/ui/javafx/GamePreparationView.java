package com.renan.decoratorspacecraft.ui.javafx;

import com.renan.decoratorspacecraft.domain.model.ShipStats;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import com.renan.decoratorspacecraft.factory.SpacecraftFactory;
import com.renan.decoratorspacecraft.service.UpgradeService;
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
import java.util.Objects;
import java.util.function.Consumer;

public class GamePreparationView {

    private static final String BACKGROUND = "-fx-background-color: linear-gradient(to bottom, #07152d, #0b2344);";
    private static final String CARD = "-fx-background-color: rgba(17, 44, 79, 0.92);"
            + "-fx-background-radius: 16; -fx-border-color: #28527a; -fx-border-radius: 16;";
    private static final String PRIMARY_BUTTON = "-fx-background-color: #23b5d3; -fx-text-fill: white;"
            + "-fx-font-weight: bold; -fx-background-radius: 10; -fx-cursor: hand;";
    private static final String UPGRADE_BUTTON = "-fx-background-color: #173e6a; -fx-text-fill: #d9f4ff;"
            + "-fx-font-weight: bold; -fx-alignment: CENTER_LEFT; -fx-background-radius: 10; -fx-cursor: hand;";

    private final SpacecraftFactory spacecraftFactory;
    private final UpgradeService upgradeService;
    private final Consumer<Spacecraft> onStartBattle;
    private Spacecraft spacecraft;

    private Label descriptionLabel;
    private Label healthValue;
    private Label attackValue;
    private Label defenseValue;
    private Label speedValue;
    private Label statusLabel;

    public GamePreparationView(
            SpacecraftFactory spacecraftFactory,
            UpgradeService upgradeService,
            Consumer<Spacecraft> onStartBattle
    ) {
        this.spacecraftFactory = Objects.requireNonNull(spacecraftFactory);
        this.upgradeService = Objects.requireNonNull(upgradeService);
        this.onStartBattle = Objects.requireNonNull(onStartBattle);
        this.spacecraft = spacecraftFactory.createBasicSpacecraft();
    }

    public BorderPane createContent() {
        BorderPane root = new BorderPane();
        root.setStyle(BACKGROUND);
        root.setPadding(new Insets(28, 42, 28, 42));

        root.setTop(createHeader());
        root.setCenter(createMainContent());
        root.setBottom(createFooter());
        updateShipDetails();

        return root;
    }

    private VBox createHeader() {
        Label eyebrow = new Label("MISSÃO 01  •  PREPARAÇÃO DE COMBATE");
        eyebrow.setStyle("-fx-text-fill: #64d8f2; -fx-font-weight: bold; -fx-letter-spacing: 1.5px;");

        Label title = new Label("DECORATOR SPACECRAFT");
        title.setTextFill(javafx.scene.paint.Color.WHITE);
        title.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 30));

        Label subtitle = new Label("Instale módulos na Explorer e monte sua nave antes da batalha.");
        subtitle.setStyle("-fx-text-fill: #b9c9dc; -fx-font-size: 14px;");

        VBox header = new VBox(5, eyebrow, title, subtitle);
        header.setPadding(new Insets(0, 0, 22, 0));
        return header;
    }

    private HBox createMainContent() {
        VBox shipPanel = createShipPanel();
        VBox upgradePanel = createUpgradePanel();

        HBox content = new HBox(22, shipPanel, upgradePanel);
        HBox.setHgrow(shipPanel, Priority.ALWAYS);
        HBox.setHgrow(upgradePanel, Priority.ALWAYS);
        content.setAlignment(Pos.TOP_CENTER);
        return content;
    }

    private VBox createShipPanel() {
        Label panelTitle = sectionTitle("NAVE ATUAL");

        Label shipIcon = new Label("✦");
        shipIcon.setStyle("-fx-text-fill: #64d8f2; -fx-font-size: 62px;");

        descriptionLabel = new Label();
        descriptionLabel.setWrapText(true);
        descriptionLabel.setStyle("-fx-text-fill: white; -fx-font-size: 19px; -fx-font-weight: bold;");

        Label activeDecorators = new Label("DECORATORS ATIVOS");
        activeDecorators.setStyle("-fx-text-fill: #6f93bc; -fx-font-size: 11px; -fx-font-weight: bold;");

        GridPane statsGrid = new GridPane();
        statsGrid.setHgap(10);
        statsGrid.setVgap(10);
        statsGrid.getColumnConstraints().addAll(column(), column());

        healthValue = statValue("VIDA", "#64d8f2", statsGrid, 0, 0);
        attackValue = statValue("ATAQUE", "#ffbd59", statsGrid, 1, 0);
        defenseValue = statValue("DEFESA", "#8ae68a", statsGrid, 0, 1);
        speedValue = statValue("VELOCIDADE", "#d08cff", statsGrid, 1, 1);

        VBox panel = new VBox(12, panelTitle, shipIcon, descriptionLabel, activeDecorators, statsGrid);
        panel.setStyle(CARD);
        panel.setPadding(new Insets(24));
        panel.setPrefWidth(430);
        panel.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(statsGrid, Priority.ALWAYS);
        return panel;
    }

    private VBox createUpgradePanel() {
        Label panelTitle = sectionTitle("MÓDULOS DISPONÍVEIS");
        Label explanation = new Label("Cada botão envolve a nave atual com um novo Decorator.");
        explanation.setWrapText(true);
        explanation.setStyle("-fx-text-fill: #b9c9dc; -fx-font-size: 13px;");

        VBox buttons = new VBox(10,
                upgradeButton("⚡  TURBO", "+10 velocidade", "TURBO"),
                upgradeButton("✹  LASER", "+15 ataque", "LASER"),
                upgradeButton("⬡  ESCUDO", "+15 defesa", "ESCUDO"),
                upgradeButton("✦  MÍSSIL", "40 de dano • uso único", "MISSIL"),
                upgradeButton("✚  REPARO", "+5 vida por rodada", "REPARO")
        );

        VBox panel = new VBox(14, panelTitle, explanation, buttons);
        panel.setStyle(CARD);
        panel.setPadding(new Insets(24));
        panel.setPrefWidth(430);
        panel.setMaxWidth(Double.MAX_VALUE);
        return panel;
    }

    private VBox createFooter() {
        statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: #93b7d8; -fx-font-size: 13px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button startBattle = new Button("INICIAR BATALHA  →");
        startBattle.setStyle(PRIMARY_BUTTON + "-fx-padding: 13 24 13 24;");
        startBattle.setOnAction(event -> onStartBattle.accept(spacecraft));

        HBox footerContent = new HBox(16, statusLabel, spacer, startBattle);
        footerContent.setAlignment(Pos.CENTER_LEFT);

        VBox footer = new VBox(footerContent);
        footer.setPadding(new Insets(22, 0, 0, 0));
        return footer;
    }

    private Button upgradeButton(String name, String effect, String upgradeCode) {
        Label buttonText = new Label(name + "\n" + effect);
        buttonText.setStyle("-fx-text-fill: #d9f4ff; -fx-font-weight: bold; -fx-font-size: 13px;");

        Button button = new Button();
        button.setGraphic(buttonText);
        button.setStyle(UPGRADE_BUTTON + "-fx-padding: 12 16 12 16;");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(event -> applyUpgrade(upgradeCode));
        return button;
    }

    private void applyUpgrade(String upgradeCode) {
        spacecraft = upgradeService.applyUpgrade(spacecraft, upgradeCode);
        updateShipDetails();
        statusLabel.setText("Módulo " + upgradeCode + " instalado com sucesso.");
    }

    private void updateShipDetails() {
        ShipStats stats = spacecraft.getStats();
        descriptionLabel.setText(spacecraft.getDescription());
        healthValue.setText(String.valueOf(stats.health()));
        attackValue.setText(String.valueOf(stats.attack()));
        defenseValue.setText(String.valueOf(stats.defense()));
        speedValue.setText(String.valueOf(stats.speed()));
    }

    private Label statValue(String label, String color, GridPane grid, int column, int row) {
        Label name = new Label(label);
        name.setStyle("-fx-text-fill: #9db4ce; -fx-font-size: 10px; -fx-font-weight: bold;");
        Label value = new Label("0");
        value.setStyle("-fx-text-fill: " + color + "; -fx-font-size: 22px; -fx-font-weight: bold;");

        VBox statCard = new VBox(3, name, value);
        statCard.setStyle("-fx-background-color: #0d294d; -fx-background-radius: 10;");
        statCard.setPadding(new Insets(12));
        statCard.setMaxWidth(Double.MAX_VALUE);
        grid.add(statCard, column, row);
        return value;
    }

    private Label sectionTitle(String text) {
        Label title = new Label(text);
        title.setStyle("-fx-text-fill: #64d8f2; -fx-font-size: 12px; -fx-font-weight: bold;");
        return title;
    }

    private ColumnConstraints column() {
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(50);
        return column;
    }
}
