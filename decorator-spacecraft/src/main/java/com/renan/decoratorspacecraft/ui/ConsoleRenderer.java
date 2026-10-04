package com.renan.decoratorspacecraft.ui;

import com.renan.decoratorspacecraft.domain.battle.Battle;
import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import java.io.PrintStream;

public class ConsoleRenderer {

    private final PrintStream output;

    public ConsoleRenderer(PrintStream output) {
        this.output = output;
    }

    public void showWelcome() {
        output.println("=====================================");
        output.println("       DECORATOR SPACECRAFT");
        output.println("=====================================");
    }

    public void showMainMenu() {
        output.println("1 - Iniciar missão");
        output.println("2 - Como funciona");
        output.println("0 - Sair");
        output.print("Escolha: ");
    }

    public void showHowItWorks() {
        output.println("A nave começa básica e recebe melhorias dinamicamente.");
        output.println("Cada melhoria é um Decorator que envolve a nave atual.");
    }

    public void showUpgradeMenu(Spacecraft spacecraft) {
        output.println();
        output.println("Nave atual: " + spacecraft.getDescription());
        output.println("1 - Instalar Turbo");
        output.println("2 - Instalar Laser");
        output.println("3 - Instalar Escudo");
        output.println("4 - Instalar Míssil");
        output.println("5 - Instalar Reparo");
        output.println("6 - Iniciar batalha");
        output.print("Escolha: ");
    }

    public void showSpacecraft(Spacecraft spacecraft) {
        output.println("Configuração: " + spacecraft.getDescription());
        output.println("Vida: " + spacecraft.getStats().health());
        output.println("Ataque: " + spacecraft.getStats().attack());
        output.println("Defesa: " + spacecraft.getStats().defense());
        output.println("Velocidade: " + spacecraft.getStats().speed());
    }

    public void showBattleStatus(Battle battle, Spacecraft spacecraft) {
        output.println();
        output.println("--- BATALHA ---");
        output.println(spacecraft.getDescription() + " | Vida: " + battle.getPlayerHealth());
        output.println("Destroyer X | Vida: " + battle.getEnemyHealth());
    }

    public void showBattleMenu(boolean missileAvailable) {
        output.println("1 - Atacar");
        if (missileAvailable) {
            output.println("2 - Usar míssil");
        }
        output.println("0 - Abandonar missão");
        output.print("Escolha: ");
    }

    public void showMessage(String message) {
        output.println(message);
    }
}
