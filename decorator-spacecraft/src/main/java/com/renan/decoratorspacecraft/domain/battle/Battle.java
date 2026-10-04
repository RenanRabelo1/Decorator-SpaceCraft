package com.renan.decoratorspacecraft.domain.battle;

import com.renan.decoratorspacecraft.domain.spacecraft.Spacecraft;
import java.util.Objects;

public class Battle {

    private final Spacecraft player;
    private final EnemyShip enemy;
    private int playerHealth;
    private int enemyHealth;

    public Battle(Spacecraft player, EnemyShip enemy) {
        this.player = Objects.requireNonNull(player);
        this.enemy = Objects.requireNonNull(enemy);
        this.playerHealth = player.getStats().health();
        this.enemyHealth = enemy.getStats().health();
    }

    public int playerAttack() {
        int damage = calculateDamage(player.getStats().attack(), enemy.getStats().defense());
        enemyHealth = Math.max(0, enemyHealth - damage);
        return damage;
    }

    public int enemyAttack() {
        int damage = calculateDamage(enemy.getStats().attack(), player.getStats().defense());
        playerHealth = Math.max(0, playerHealth - damage);
        return damage;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public BattleResult getResult() {
        if (enemyHealth == 0) {
            return BattleResult.PLAYER_VICTORY;
        }
        if (playerHealth == 0) {
            return BattleResult.ENEMY_VICTORY;
        }
        return BattleResult.IN_PROGRESS;
    }

    private int calculateDamage(int attack, int defense) {
        return Math.max(1, attack - defense);
    }
}
