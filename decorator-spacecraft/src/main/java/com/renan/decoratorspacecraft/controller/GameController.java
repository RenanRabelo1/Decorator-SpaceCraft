package com.renan.decoratorspacecraft.controller;

import com.renan.decoratorspacecraft.domain.battle.Battle;
import com.renan.decoratorspacecraft.domain.battle.BattleResult;
import com.renan.decoratorspacecraft.domain.battle.EnemyShip;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import com.renan.decoratorspacecraft.exception.InvalidOptionException;
import com.renan.decoratorspacecraft.factory.SpacecraftFactory;
import com.renan.decoratorspacecraft.service.BattleService;
import com.renan.decoratorspacecraft.service.UpgradeService;
import com.renan.decoratorspacecraft.ui.ConsoleMenu;
import com.renan.decoratorspacecraft.ui.ConsoleRenderer;
import java.util.Scanner;

public class GameController {

    private final ConsoleMenu menu;
    private final ConsoleRenderer renderer;
    private final SpacecraftFactory spacecraftFactory;
    private final UpgradeService upgradeService;
    private final BattleService battleService;

    public GameController() {
        this(
                new ConsoleMenu(new Scanner(System.in)),
                new ConsoleRenderer(System.out),
                new SpacecraftFactory(),
                new UpgradeService(),
                new BattleService()
        );
    }

    public GameController(
            ConsoleMenu menu,
            ConsoleRenderer renderer,
            SpacecraftFactory spacecraftFactory,
            UpgradeService upgradeService,
            BattleService battleService
    ) {
        this.menu = menu;
        this.renderer = renderer;
        this.spacecraftFactory = spacecraftFactory;
        this.upgradeService = upgradeService;
        this.battleService = battleService;
    }

    public void start() {
        boolean running = true;
        renderer.showWelcome();

        while (running) {
            renderer.showMainMenu();
            Integer option = readOption();
            if (option == null) {
                continue;
            }

            switch (option) {
                case 1 -> startMission();
                case 2 -> renderer.showHowItWorks();
                case 0 -> running = false;
                default -> renderer.showMessage("Opção inválida.");
            }
        }

        renderer.showMessage("Até a próxima missão.");
    }

    private void startMission() {
        Spacecraft spacecraft = spacecraftFactory.createBasicSpacecraft();
        boolean preparing = true;

        while (preparing) {
            renderer.showUpgradeMenu(spacecraft);
            Integer option = readOption();
            if (option == null) {
                continue;
            }

            switch (option) {
                case 1 -> spacecraft = upgradeService.applyUpgrade(spacecraft, "TURBO");
                case 2 -> spacecraft = upgradeService.applyUpgrade(spacecraft, "LASER");
                case 3 -> spacecraft = upgradeService.applyUpgrade(spacecraft, "ESCUDO");
                case 4 -> spacecraft = upgradeService.applyUpgrade(spacecraft, "MISSIL");
                case 5 -> spacecraft = upgradeService.applyUpgrade(spacecraft, "REPARO");
                case 6 -> {
                    renderer.showSpacecraft(spacecraft);
                    runBattle(spacecraft);
                    preparing = false;
                }
                default -> renderer.showMessage("Opção inválida.");
            }
        }
    }

    private void runBattle(Spacecraft spacecraft) {
        Battle battle = new Battle(spacecraft, new EnemyShip());

        while (battle.getResult() == BattleResult.IN_PROGRESS) {
            renderer.showBattleStatus(battle, spacecraft);
            renderer.showBattleMenu(battle.isMissileAvailable());
            Integer option = readOption();
            if (option == null) {
                continue;
            }

            if (option == 0) {
                renderer.showMessage("Missão abandonada.");
                return;
            }

            if (option == 1) {
                int damage = battleService.performPlayerAttack(battle);
                renderer.showMessage("Ataque causou " + damage + " de dano.");
            } else if (option == 2 && battle.isMissileAvailable()) {
                int damage = battleService.performPlayerMissileAttack(battle);
                renderer.showMessage("Míssil causou " + damage + " de dano.");
            } else {
                renderer.showMessage("Opção inválida.");
                continue;
            }

            if (battle.getResult() == BattleResult.IN_PROGRESS) {
                int damage = battleService.performEnemyTurn(battle);
                renderer.showMessage("Inimigo causou " + damage + " de dano.");
            }
        }

        if (battle.getResult() == BattleResult.PLAYER_VICTORY) {
            renderer.showMessage("Vitória! A Destroyer X foi derrotada.");
        } else {
            renderer.showMessage("Derrota! Sua nave foi destruída.");
        }
    }

    private Integer readOption() {
        try {
            return menu.readOption();
        } catch (InvalidOptionException exception) {
            renderer.showMessage(exception.getMessage());
            return null;
        }
    }
}
